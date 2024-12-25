package com.TravelAGency.TravelAgency.Event;

import com.TravelAGency.TravelAgency.hotel.HotelModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

public class EventModel {

    private int id;
    @JsonProperty("event_name")
    private String eventName;
    @JsonProperty("type")
    private String event_type;
    private String address;

    public int getTicket() {
        return tickets;
    }

    public void setTicket(int ticket) {
        this.tickets = tickets;
    }

    @JsonProperty("tickets")
    private int tickets;
    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

