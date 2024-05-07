package com.app.gurme.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
//BİTTİ
@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer menu_id;

    @Column(name = "restaurant_id")
    private Integer restaurant_id;

    @Column(name = "item_name")
    private String item_name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;



    public Menu() {
    }

    public Menu(Integer restaurantId, String item_name, double price, String description, String image, String category, List<String> ingredients) {
        super();
        this.restaurant_id = restaurantId;
        this.item_name = item_name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", restaurant_id=" + restaurant_id +
                ", item_name='" + item_name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
