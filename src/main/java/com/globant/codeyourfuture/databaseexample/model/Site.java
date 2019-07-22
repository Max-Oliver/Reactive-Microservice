package com.globant.codeyourfuture.databaseexample.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "sites")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "site_id")
    private Long siteId;
    @Column(name = "url")
    private String url;
    @Column(name = "theme_id")
    private Long themeId;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_on")
    private Instant createdOn;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "site")
    @JsonManagedReference
    private SiteAdvancedSetting advancedSetting;

    @Transient
    private Set<Vendor> attachedVendors;

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(final Long siteId) {
        this.siteId = siteId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(final Long themeId) {
        this.themeId = themeId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final Instant createdOn) {
        this.createdOn = createdOn;
    }

    public SiteAdvancedSetting getAdvancedSetting() {
        return advancedSetting;
    }

    public void setAdvancedSetting(final SiteAdvancedSetting advancedSetting) {
        this.advancedSetting = advancedSetting;
    }

    public Set<Vendor> getAttachedVendors() {
        return attachedVendors;
    }

    public void setAttachedVendors(final Set<Vendor> attachedVendors) {
        this.attachedVendors = attachedVendors;
    }
}
