package com.example.RESTfulServices.models;

public class Booking {

    private Long resourceId;
    private String email;
    private Flight flight;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "resourceId=" + resourceId +
                ", email='" + email + '\'' +
                ", flight=" + flight +
                '}';
    }
}
