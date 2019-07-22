package com.globant.codeyourfuture.databaseexample.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "site_advanced_settings")
public class SiteAdvancedSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "settings_id")
    @JsonIgnore
    private Long settingsId;
    @Column(name = "site_name")
    private String siteName;
    @Column(name = "google_personalization")
    private Boolean googlePersonalization;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "site_id")
    @JsonBackReference
    private Site site;

    public Long getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(final Long settingsId) {
        this.settingsId = settingsId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(final String siteName) {
        this.siteName = siteName;
    }

    public Boolean getGooglePersonalization() {
        return googlePersonalization;
    }

    public void setGooglePersonalization(final Boolean googlePersonalization) {
        this.googlePersonalization = googlePersonalization;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(final Site site) {
        this.site = site;
    }
}
