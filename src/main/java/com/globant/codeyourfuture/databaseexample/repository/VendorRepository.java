package com.globant.codeyourfuture.databaseexample.repository;

import com.globant.codeyourfuture.databaseexample.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
