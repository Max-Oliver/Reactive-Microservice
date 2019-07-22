package com.globant.codeyourfuture.databaseexample.service;

import com.globant.codeyourfuture.databaseexample.model.Event;
import com.globant.codeyourfuture.databaseexample.model.Site;
import com.globant.codeyourfuture.databaseexample.model.SiteAdvancedSetting;
import com.globant.codeyourfuture.databaseexample.model.Vendor;
import com.globant.codeyourfuture.databaseexample.repository.SiteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class SiteService {

    private static final Logger LOGGER = LogManager.getLogger(SiteService.class);

    private final SiteRepository repository;
    private final VendorService vendorService;
    private final EventService eventService;

    @Autowired
    public SiteService(final SiteRepository repository,
                       final VendorService vendorService,
                       final EventService eventService) {
        this.repository = repository;
        this.vendorService = vendorService;
        this.eventService = eventService;
    }

    public Mono<Site> createSite(final Site site) {
        site.setSiteId(null);
        site.setCreatedOn(Instant.now());
        final SiteAdvancedSetting setting = new SiteAdvancedSetting();
        setting.setSiteName("PLEASE SPECIFY SITE NAME");
        setting.setGooglePersonalization(false);

        setting.setSite(site);
        site.setAdvancedSetting(setting);

        return Mono.just(repository.saveAndFlush(site))
                .doOnNext(savedSite -> {
                    final Event siteCreated = Event.createSiteCreatedEvent("CREAMOS UN SITE " + savedSite.getUrl());
                    eventService
                            .deliverEvent(siteCreated)
                            .doOnNext(event -> {
                                if (event.getDelivered()) {
                                    LOGGER.info("ENVIAMOS EL EVENT: " + event.getEventType() + " " + event.getEventTimestamp());
                                }
                                else {
                                    throw new RuntimeException("NO SE ENTREGO EL EVENTO");
                                }
                            })
                            .doOnError(LOGGER::error)
                            .doOnComplete(() -> LOGGER.info("COMPLETAMOS EL DELIVERY"))
                            .subscribe();
                });
    }

    public Flux<Site> findAllSites() {
        return Flux.fromIterable(repository.findAll());
    }

    public Mono<Site> attachVendorToSite(final Long siteId,
                                         final Long vendorId) {
        return Mono.justOrEmpty(repository.findById(siteId))
                .zipWith(vendorService.findVendor(vendorId), (site, vendor) -> {
                    if (CollectionUtils.isEmpty(site.getAttachedVendors())) {
                        site.setAttachedVendors(new HashSet<>());
                    }
                    site.getAttachedVendors().add(vendor);
                    return site;
                })
                .map(repository::save);
    }
}
