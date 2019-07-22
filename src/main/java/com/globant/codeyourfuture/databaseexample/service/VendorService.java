package com.globant.codeyourfuture.databaseexample.service;

import com.globant.codeyourfuture.databaseexample.model.Vendor;
import com.globant.codeyourfuture.databaseexample.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository repository;

    @Autowired
    public VendorService(VendorRepository repository) {
        this.repository = repository;
    }

    public Mono<Vendor> createVendor(final Vendor vendor) {
        vendor.setVendorId(null);
        return Mono.just(repository.save(vendor));
    }

    public Flux<Vendor> findAllVendors() {
        return Flux.fromIterable(repository.findAll());
    }

    public Mono<Vendor> findVendor(final Long vendorId) {
        return Mono.justOrEmpty(repository.findById(vendorId));
    }

}
