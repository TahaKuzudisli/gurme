package com.app.gurme.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class GurmeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gurme_order_id")
    private Long gurme_order_id;

    @Column(name = "restaurant_id")
    private Long restaurant_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "delivery_person_id")
    private Long delivery_person_id;

    @Column(name = "menu_id")
    private Long menu_id;

    @Column(name = "note")
    private String note;

    @Column(name = "total_price")
    private double total_price;

    // hazırlanıyor/yolda/teslim edildi/iptal
    @Column(name = "order_situation")
    private String order_situation;

    //saat ve günün tarihini tutacak
    @Column(name = "order_time")
    private String order_time;

    public GurmeOrder() {
    }

    public GurmeOrder(Long restaurant_id, Long user_id, Long delivery_person_id, Long menu_id, String note, double total_price, String order_situation, String order_time) {
        this.restaurant_id = restaurant_id;
        this.user_id = user_id;
        this.delivery_person_id = delivery_person_id;
        this.menu_id = menu_id;
        this.note = note;
        this.total_price = total_price;
        this.order_situation = order_situation;
        this.order_time = order_time;
    }

    public Long getGurme_order_id() {
        return gurme_order_id;
    }

    public void setGurme_order_id(Long gurme_order_id) {
        this.gurme_order_id = gurme_order_id;
    }

    public Long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Long menu_id) {
        this.menu_id = menu_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getOrder_situation() {
        return order_situation;
    }

    public void setOrder_situation(String order_situation) {
        this.order_situation = order_situation;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public Long getDelivery_person_id() {
        return delivery_person_id;
    }

    public void setDelivery_person_id(Long delivery_person_id) {
        this.delivery_person_id = delivery_person_id;
    }

    @Override
    public String toString() {
        return "GurmeOrder{" +
                "gurme_order_id=" + gurme_order_id +
                ", restaurant_id=" + restaurant_id +
                ", user_id=" + user_id +
                ", delivery_person_id=" + delivery_person_id +
                ", menu_id=" + menu_id +
                ", note='" + note + '\'' +
                ", total_price=" + total_price +
                ", order_situation='" + order_situation + '\'' +
                ", order_time='" + order_time + '\'' +
                '}';
    }
}
