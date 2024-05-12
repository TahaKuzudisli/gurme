package com.app.gurme.entities;


import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long order_item_id;

    @Column(name = "gurme_order_id")
    private Long gurme_order_id;

    @JoinColumn(name = "menu_id")
    private Integer menu_id;


    @Column(name = "ingredients_to_remove")
    private String ingredients_to_remove;

    public OrderItem() {
    }

    public OrderItem(Long gurme_order_id, Integer menu_id, String ingredients_to_remove) {
        this.gurme_order_id = gurme_order_id;
        this.menu_id = menu_id;
        this.ingredients_to_remove = ingredients_to_remove;
    }

    public Long getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(Long order_item_id) {
        this.order_item_id = order_item_id;
    }

    public Long getGurme_order_id() {
        return gurme_order_id;
    }

    public void setGurme_order_id(Long gurme_order_id) {
        this.gurme_order_id = gurme_order_id;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }


    public String getIngredients_to_remove() {
        return ingredients_to_remove;
    }

    public void setIngredients_to_remove(String ingredients_to_remove) {
        this.ingredients_to_remove = ingredients_to_remove;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_item_id=" + order_item_id +
                ", gurme_order_id=" + gurme_order_id +
                ", menu_id=" + menu_id +
                ", ingredients_to_remove='" + ingredients_to_remove + '\'' +
                '}';
    }
}
