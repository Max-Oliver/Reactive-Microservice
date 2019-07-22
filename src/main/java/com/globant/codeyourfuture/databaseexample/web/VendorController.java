package com.globant.codeyourfuture.databaseexample.web;

import com.globant.codeyourfuture.databaseexample.model.Vendor;
import com.globant.codeyourfuture.databaseexample.service.VendorService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
public class VendorController {

    private final VendorService service;

    @Autowired
    public VendorController(final VendorService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Publisher<Vendor> postVendor(@RequestBody final Vendor vendor) {
        return service.createVendor(vendor);
    }

    @GetMapping
    public Publisher<Vendor> getVendors() {
        return service.findAllVendors();
    }
}
