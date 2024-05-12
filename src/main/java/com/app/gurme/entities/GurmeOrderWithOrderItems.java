package com.app.gurme.entities;

import java.util.List;

public class GurmeOrderWithOrderItems {

    private GurmeOrder gurmeOrder;
    private List<OrderItem> orderItemList;

    public GurmeOrderWithOrderItems(GurmeOrder gurmeOrder, List<OrderItem> orderItemList) {
        this.gurmeOrder = gurmeOrder;
        this.orderItemList = orderItemList;
    }

    public GurmeOrder getGurmeOrder() {
        return gurmeOrder;
    }

    public void setGurmeOrder(GurmeOrder gurmeOrder) {
        this.gurmeOrder = gurmeOrder;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
