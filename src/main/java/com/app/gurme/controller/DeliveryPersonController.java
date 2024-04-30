package com.app.gurme.controller;

import com.app.gurme.entities.DeliveryPerson;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.DeliveryPersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeliveryPersonController {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @GetMapping("/deliverypersons")
    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersonRepository.findAll();
    }

    @GetMapping("/deliverypersons/{id}")
    public ResponseEntity<DeliveryPerson> getDeliveryPersonById(@PathVariable Long deliveryPersonId) throws ResourceNotFoundException {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryPersonId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Person not found for this id:" + deliveryPersonId));
        return ResponseEntity.ok().body(deliveryPerson);}

    @PostMapping("/deliverypersons")
    public DeliveryPerson createDeliveryPerson(@RequestBody DeliveryPerson deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }

    @PutMapping("/deliverypersons/{id}")
    public ResponseEntity<DeliveryPerson> updateDeliveryPerson
            (@PathVariable(value = "id") Long deliveryPersonId,
             @Valid @RequestBody DeliveryPerson deliveryPersonDetails)
            throws ResourceNotFoundException  {

        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryPersonId)
                .orElseThrow(() -> new ResourceNotFoundException("GurmeOrder not found for this id :: " + deliveryPersonId));

        deliveryPerson.setFirst_name(deliveryPersonDetails.getFirst_name());
        deliveryPerson.setLast_name(deliveryPersonDetails.getLast_name());
        deliveryPerson.setPhone_number(deliveryPersonDetails.getPhone_number());
        deliveryPerson.setEmail(deliveryPersonDetails.getEmail());
        deliveryPerson.setPassword(deliveryPersonDetails.getPassword());


        final DeliveryPerson updateDeliveryPerson = deliveryPersonRepository.save(deliveryPerson);
        return ResponseEntity.ok(updateDeliveryPerson);

    }

    @DeleteMapping("/deliverypersons/{id}")
    public Map<String, Boolean> deleteDeliveryPerson(@PathVariable(value = "id") Long deliveryPersonId)
            throws ResourceNotFoundException {
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryPersonId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery person not found for this id :: " + deliveryPersonId));
        deliveryPersonRepository.delete(deliveryPerson);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
