package com.app.gurme.controller;


import com.app.gurme.entities.Restaurant;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestaurantController {


    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value = "id") Integer restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id:" + restaurantId));
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @PutMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value = "id") Integer restaurantId,
                                                       @Valid @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));

        restaurant.setRestaurant_name(restaurantDetails.getRestaurant_name());
        restaurant.setAddress(restaurantDetails.getAddress());
        restaurant.setEmail(restaurantDetails.getEmail());
        restaurant.setPhoneNumber(restaurantDetails.getPhoneNumber());
        restaurant.setImage(restaurantDetails.getImage());
        restaurant.setOpeningHours(restaurantDetails.getOpeningHours());
        restaurant.setClosingHours(restaurantDetails.getClosingHours());

        final Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/restaurants/{id}")
    public Map<String, Boolean> deleteRestaurant(@PathVariable(value = "id") Integer restaurantId)
            throws ResourceNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id :: " + restaurantId));
        restaurantRepository.delete(restaurant);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
