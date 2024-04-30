package com.app.gurme.entities;

import java.util.List;

public class DeliveryPersonsOrders {

    private DeliveryPerson deliveryPerson;
    private List<GurmeOrder> gurmeOrders;


    public DeliveryPersonsOrders(DeliveryPerson deliveryPerson, List<GurmeOrder> gurmeOrders) {
        this.deliveryPerson = deliveryPerson;
        this.gurmeOrders = gurmeOrders;
    }


    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public List<GurmeOrder> getGurmeOrders() {
        return gurmeOrders;
    }

    public void setGurmeOrders(List<GurmeOrder> gurmeOrders) {
        this.gurmeOrders = gurmeOrders;
    }
}
