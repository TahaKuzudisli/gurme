package com.app.gurme.controller;


import com.app.gurme.entities.DeliveryPerson;
import com.app.gurme.entities.DeliveryPersonsOrders;
import com.app.gurme.entities.GurmeOrder;
import com.app.gurme.entities.RestaurantWithMenus;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.DeliveryPersonRepository;
import com.app.gurme.repos.GurmeOrderRepository;
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
public class DeliveryPersonsOrdersController {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private GurmeOrderRepository gurmeOrderRepository;


    @GetMapping("/deliverypersonsorders/{delivery_person_id}")
    public ResponseEntity<DeliveryPersonsOrders> getRestaurantWithMenus(@PathVariable(value = "delivery_person_id") Long delivery_person_id) throws ResourceNotFoundException {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(delivery_person_id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery person not found with id: " + delivery_person_id));

        List<GurmeOrder> gurmeOrders = gurmeOrderRepository.findByDeliveryPersonId(delivery_person_id);

        DeliveryPersonsOrders deliveryPersonsOrders = new DeliveryPersonsOrders(deliveryPerson, gurmeOrders);

        return ResponseEntity.ok().body(deliveryPersonsOrders);
    }

}
