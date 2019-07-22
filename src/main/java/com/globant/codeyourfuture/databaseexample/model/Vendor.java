package com.globant.codeyourfuture.databaseexample.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "vendor_id")
    private Long vendorId;
    @NonNull
    @Column(name = "vendor_name")
    private String name;
    @Column(name = "vendor_description")
    private String description;
    @Column(name = "privacy_policy_url")
    private String privacyPolicyUrl;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(final Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(final String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        final Vendor other = (Vendor) o;
        return this.vendorId.equals(other.getVendorId())
                && this.name.equals(other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorId, name);
    }
}
