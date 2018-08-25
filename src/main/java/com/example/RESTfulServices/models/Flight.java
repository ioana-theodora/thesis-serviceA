package com.example.RESTfulServices.models;

import java.util.Date;

public class Flight {

    private Long resourceId;
    private Airport destination;
    private Airport departure;
    private Date departureTime;
    private Date arrivalTime;
    private Long cost;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public Airport getDeparture() {
        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "resourceId=" + resourceId +
                ", destination=" + destination +
                ", departure=" + departure +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", cost=" + cost +
                '}';
    }

}
