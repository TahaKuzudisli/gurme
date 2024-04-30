package com.app.gurme.entities;

import java.util.List;

public class RestaurantWithMenus {

private Restaurant restaurant;
private List<Menu> menus;


    public RestaurantWithMenus(Restaurant restaurant, List<Menu> menus) {
        this.restaurant = restaurant;
        this.menus = menus;
    }


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
