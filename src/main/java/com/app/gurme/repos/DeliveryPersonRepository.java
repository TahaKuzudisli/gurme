package com.app.gurme.repos;

import com.app.gurme.entities.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepository extends JpaRepository <DeliveryPerson, Long> {
}
