package com.TravelAGency.TravelAgency.Event;


public class EventDto {

    private Long id;
    private String eventName;
    private String eventType;
    private String address;
    private int tickets;



    public EventDto(Long id, String eventName, String eventType, String address,int tickets) {
        this.id = id;
        this.eventName = eventName;
        this.eventType = eventType;
        this.address = address;
        this.tickets = tickets;
    }

    // Getters and Setters
    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
