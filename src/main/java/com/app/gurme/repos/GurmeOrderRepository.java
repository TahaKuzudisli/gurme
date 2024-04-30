package com.app.gurme.repos;

import com.app.gurme.entities.GurmeOrder;
import com.app.gurme.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GurmeOrderRepository extends JpaRepository <GurmeOrder, Long> {
    @Query("SELECT c FROM GurmeOrder c WHERE c.delivery_person_id = :delivery_person_id")
    List<GurmeOrder> findByDeliveryPersonId(@Param("delivery_person_id") Long delivery_person_id);
}
