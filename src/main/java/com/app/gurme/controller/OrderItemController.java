package com.app.gurme.controller;

import com.app.gurme.entities.OrderItem;
import com.app.gurme.repos.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    // Get a single order item by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new order item
    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    // Update an existing order item
    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable Long id, @RequestBody OrderItem orderItemDetails) {
        return orderItemRepository.findById(id)
                .map(orderItem -> {
                    orderItem.setGurme_order_id(orderItemDetails.getGurme_order_id());
                    orderItem.setMenu_id(orderItemDetails.getMenu_id());
                    orderItem.setIngredients_to_remove(orderItemDetails.getIngredients_to_remove());
                    OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
                    return ResponseEntity.ok(updatedOrderItem);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an order item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        return orderItemRepository.findById(id)
                .map(orderItem -> {
                    orderItemRepository.delete(orderItem);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
