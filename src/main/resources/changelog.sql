--liquibase formatted sql

--changeSet maximiliano.olivero@globant.com:create_sites_table codeyourfuturedb:1
CREATE TABLE sites (
                       site_id BIGINT PRIMARY KEY,
                       url VARCHAR(255),
                       theme_id BIGINT,
                       created_BY TEXT,
                       created_on TIMESTAMP WITH TIME ZONE
);
-- rollback DROP TABLE sites;

--changeSet maximiliano.olivero@globant.com:update_sites_with_updated_by
ALTER TABLE sites ADD COLUMN updated_by TEXT;
--

--changeSet maximiliano.olivero@globant.com:create_hibernate_sequence
CREATE SEQUENCE hibernate_sequence CYCLE;
--

--changeSet maximiliano.olivero@globant.com:create_site_advanced_settings_table
CREATE TABLE site_advanced_settings (
    settings_id BIGINT,
    site_id BIGINT,
    site_name TEXT,
    google_personalization BOOLEAN
);


-- changeSet maximiliano.olivero@globant.com:add_site_advanced_settings_foreign_key
ALTER TABLE site_advanced_settings
ADD CONSTRAINT site_advanced_settings_fk_sites FOREIGN KEY (site_id) REFERENCES sites(site_id);
--

-- changeSet maximiliano.olivero@globant.com:add_site_advanced_settings_unique_key
ALTER TABLE site_advanced_settings
ADD CONSTRAINT site_advanced_settings_unique_sites UNIQUE (site_id);
--

-- changeSet maximiliano.olivero@globant.com:add_vendors_table
CREATE TABLE vendors(
    vendor_id BIGINT PRIMARY KEY,
    vendor_name VARCHAR(255) NOT NULL,
    vendor_description TEXT,
    privacy_policy_url TEXT
)
--
