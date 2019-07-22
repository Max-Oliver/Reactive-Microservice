package com.globant.codeyourfuture.databaseexample.model;

import java.time.Instant;

public class Event {

    private final EventType eventType;
    private final String eventBody;
    private final Instant eventTimestamp;
    private Boolean delivered;

    private Event(final EventType eventType,
                  final String eventBody,
                  final Instant eventTimestamp) {
        this.eventType = eventType;
        this.eventBody = eventBody;
        this.eventTimestamp = eventTimestamp;
        this.delivered = false;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getEventBody() {
        return eventBody;
    }

    public Instant getEventTimestamp() {
        return eventTimestamp;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(final Boolean delivered) {
        this.delivered = delivered;
    }

    public static Event createSiteCreatedEvent(final String eventBody) {
        return new Event(EventType.SITE_CREATED, eventBody, Instant.now());
    }

    public static Event createVendorCreatedEvent(final String eventBody) {
        return new Event(EventType.VENDOR_CREATED, eventBody, Instant.now());
    }

    public enum EventType {
        SITE_CREATED, VENDOR_CREATED
    }
}
