package com.app.gurme.controller;

import com.app.gurme.entities.Menu;
import com.app.gurme.entities.Restaurant;
import com.app.gurme.entities.RestaurantWithMenus;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.MenuRepository;
import com.app.gurme.repos.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantAndMenuController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;


    @GetMapping("/gurme/{restaurantId}")
    public ResponseEntity<RestaurantWithMenus> getRestaurantWithMenus(@PathVariable(value = "restaurantId") Integer restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + restaurantId));

        List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);

        RestaurantWithMenus restaurantWithMenus = new RestaurantWithMenus(restaurant, menus);

        return ResponseEntity.ok().body(restaurantWithMenus);
    }


}
