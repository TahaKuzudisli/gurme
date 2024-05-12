package com.app.gurme.repos;

import com.app.gurme.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository <OrderItem,Long> {

    @Query("SELECT c FROM OrderItem c WHERE c.gurme_order_id = :gurmeOrderId")
    List<OrderItem> findByGurmeOrderId(@Param("gurmeOrderId") Long gurmeOrderId);
}