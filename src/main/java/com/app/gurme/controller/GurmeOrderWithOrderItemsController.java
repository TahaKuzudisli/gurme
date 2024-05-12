package com.app.gurme.controller;

import com.app.gurme.entities.*;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.GurmeOrderRepository;
import com.app.gurme.repos.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api")
public class GurmeOrderWithOrderItemsController {


    @Autowired
    private GurmeOrderRepository gurmeOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/orderwithitems/{gurmeOrderId}")
    public ResponseEntity<GurmeOrderWithOrderItems> getGurmeOrderWithOrderItems(@PathVariable(value = "gurmeOrderId") Long gurmeOrderId) throws ResourceNotFoundException {
        GurmeOrder gurmeOrder = gurmeOrderRepository.findById(gurmeOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + gurmeOrderId));

        List<OrderItem> orderItems = orderItemRepository.findByGurmeOrderId(gurmeOrderId);

        GurmeOrderWithOrderItems gurmeOrderWithOrderItems = new GurmeOrderWithOrderItems(gurmeOrder, orderItems);

        return ResponseEntity.ok().body(gurmeOrderWithOrderItems);
    }

}
