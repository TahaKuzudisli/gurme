package com.app.gurme.repos;

import com.app.gurme.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository <Restaurant, Integer> {
}
