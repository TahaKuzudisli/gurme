package com.app.gurme.controller;


import com.app.gurme.entities.Restaurant;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.RestaurantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.MalformedURLException;

import org.springframework.http.MediaType;



@RestController
@RequestMapping("/api")
public class RestaurantController {


    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    /*
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value = "id") Integer restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id:" + restaurantId));
        return ResponseEntity.ok().body(restaurant);
    }
*/


    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Map<String, Object>> getRestaurantById(@PathVariable(value = "id") Integer restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found for this id: " + restaurantId));

        Map<String, Object> response = new HashMap<>();
        response.put("restaurant", restaurant);
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/image/")
                .path(restaurant.getImage())
                .toUriString();
        response.put("imageUrl", imageUrl);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        Resource resource = resourceLoader.getResource("classpath:/static/images/" + imageName);
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            throw new RuntimeException("Image not found");
        }
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
