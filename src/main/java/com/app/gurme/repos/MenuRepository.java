package com.app.gurme.repos;

import com.app.gurme.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository <Menu, Integer> {
    @Query("SELECT c FROM Menu c WHERE c.restaurant_id = :restaurantId")
    List<Menu> findByRestaurantId(@Param("restaurantId") Integer restaurantId);
}