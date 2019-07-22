package com.globant.codeyourfuture.databaseexample.web;

import com.globant.codeyourfuture.databaseexample.model.Event;
import com.globant.codeyourfuture.databaseexample.model.Site;
import com.globant.codeyourfuture.databaseexample.repository.SiteRepository;
import com.globant.codeyourfuture.databaseexample.service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SiteControllerTests {

    private final static Logger LOGGER = LogManager.getLogger(SiteControllerTests.class);

    @Autowired
    private SiteController testController;
    @Autowired
    private SiteRepository siteRepository;

    @MockBean
    private EventService mockEventService;

    private Publisher<Site> testSites;

    @Before
    public void loadSites() {
        siteRepository.deleteAll();
        Site site1 = new Site();
        site1.setUrl("www.test1.com");
        Site site2 = new Site();
        site2.setUrl("www.test2.com");
        siteRepository.save(site1);
        siteRepository.save(site2);
    }

    @Test
    public void getAllSites_SubscribesToAllSitesInRepository() {
        testSites = testController.getAllSites();
        StepVerifier.create(testSites)
                .assertNext(site -> Assertions.assertThat(site.getUrl()).isEqualTo("www.test1.com"))
                .assertNext(site -> Assertions.assertThat(site.getUrl()).isEqualTo("www.test2.com"))
                .verifyComplete();
    }

    @Test
    public void siteCreated_EventDelivered() {
        Mockito.when(mockEventService.deliverEvent(ArgumentMatchers.any(Event.class)))
                .thenAnswer(answer -> {
                    LOGGER.info("MIRA MAMA, ENTRE EN EL ANSWER");
                    Assertions.assertThat(answer.getArguments()).isNotEmpty();
                    final Event testEvent = answer.getArgument(0);
                    Assertions.assertThat(testEvent.getEventType()).isEqualByComparingTo(Event.EventType.SITE_CREATED);
                    testEvent.setDelivered(true);
                    return Flux.just(testEvent);
                });
        Site testSite = new Site();
        testSite.setUrl("www.test.com");
        testSites = testController.postNewSite(testSite);
        StepVerifier.create(testSites)
                .assertNext(site -> Assertions.assertThat(site).isNotNull())
                .verifyComplete();
    }

    @Test
    public void siteCreated_EventNotDelivered() {
        Mockito.when(mockEventService.deliverEvent(ArgumentMatchers.any(Event.class)))
                .thenAnswer(answer -> {
                    LOGGER.info("MIRA MAMA, ENTRE EN EL ANSWER");
                    Assertions.assertThat(answer.getArguments()).isNotEmpty();
                    final Event testEvent = answer.getArgument(0);
                    Assertions.assertThat(testEvent.getEventType()).isEqualByComparingTo(Event.EventType.SITE_CREATED);
                    testEvent.setDelivered(false);
                    return Flux.just(testEvent);
                });
        Site testSite = new Site();
        testSite.setUrl("www.test.com");
        testSites = testController.postNewSite(testSite);
        StepVerifier.create(testSites)
                .assertNext(site -> Assertions.assertThat(site).isNotNull())
                .verifyComplete();
    }
}
