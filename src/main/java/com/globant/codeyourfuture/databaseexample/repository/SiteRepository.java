package com.globant.codeyourfuture.databaseexample.repository;

import com.globant.codeyourfuture.databaseexample.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
}
