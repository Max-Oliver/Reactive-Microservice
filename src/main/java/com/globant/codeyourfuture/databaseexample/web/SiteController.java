package com.globant.codeyourfuture.databaseexample.web;

import com.globant.codeyourfuture.databaseexample.model.Site;
import com.globant.codeyourfuture.databaseexample.service.SiteService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/site", produces = MediaType.APPLICATION_JSON_VALUE)
public class SiteController {

    private final SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Site> postNewSite(@RequestBody final Site newSite) {
        return siteService.createSite(newSite);
    }

    @GetMapping
    public Publisher<Site> getAllSites() {
        return siteService.findAllSites();
    }

    @PutMapping("/{siteId}/vendor/{vendorId}")
    public Publisher<Site> putVendorInSite(@PathVariable("siteId") final Long siteId,
                                           @PathVariable("vendorId") final Long vendorId) {
        return siteService.attachVendorToSite(siteId, vendorId);
    }

    @DeleteMapping("/{siteId}/vendor/{vendorId}")
    public Publisher<Site> deleteVendorFromSite(@PathVariable("siteId") final Long siteId,
                                                @PathVariable("vendorId") final Long vendorId) {
        return null;
    }
}
