package com.app.gurme.controller;


import com.app.gurme.entities.GurmeOrder;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.GurmeOrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GurmeOrderController {


    @Autowired
    private GurmeOrderRepository gurmeOrderRepository;

    @GetMapping("/orders")
    public List<GurmeOrder> getAllOrders() {
        return gurmeOrderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<GurmeOrder> getOrderById(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException {
        GurmeOrder gurmeOrder = gurmeOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("GurmeOrder not found for this id:" + orderId));
        return ResponseEntity.ok().body(gurmeOrder);
    }

    @PostMapping("/orders")
    public GurmeOrder createOrder(@Valid @RequestBody GurmeOrder gurmeOrder) {
        return gurmeOrderRepository.save(gurmeOrder);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<GurmeOrder> updateOrder(@PathVariable(value = "id") Long orderId,
                                                  @Valid @RequestBody GurmeOrder gurmeOrderDetails) throws ResourceNotFoundException {
        GurmeOrder gurmeOrder = gurmeOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("GurmeOrder not found for this id :: " + orderId));

        gurmeOrder.setOrder_situation(gurmeOrderDetails.getOrder_situation());
        gurmeOrder.setOrder_time(gurmeOrderDetails.getOrder_time());
        gurmeOrder.setNote(gurmeOrderDetails.getNote());
        gurmeOrder.setTotal_price(gurmeOrderDetails.getTotal_price());


        final GurmeOrder updatedGurmeOrder = gurmeOrderRepository.save(gurmeOrder);
        return ResponseEntity.ok(updatedGurmeOrder);
    }

    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long orderId)
            throws ResourceNotFoundException {
        GurmeOrder gurmeOrder = gurmeOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("GurmeOrder not found for this id :: " + orderId));
        gurmeOrderRepository.delete(gurmeOrder);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}

