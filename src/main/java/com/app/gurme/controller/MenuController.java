package com.app.gurme.controller;


import com.app.gurme.entities.Menu;
import com.app.gurme.exception.ResourceNotFoundException;
import com.app.gurme.repos.MenuRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @GetMapping("/menus/{id}")
    public ResponseEntity<Map<String, Object>> getMenuById(@PathVariable(value = "id") Integer menuId) throws ResourceNotFoundException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id:" + menuId));

        Map<String, Object> response = new HashMap<>();
        response.put("menu", menu);
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/menu-image/")
                .path(menu.getImage())
                .toUriString();
        response.put("imageUrl", imageUrl);

        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/menu-image/{imageName}")
    public ResponseEntity<Resource> getMenuImage(@PathVariable String imageName) {
        Resource resource = resourceLoader.getResource("classpath:/static/images/" + imageName);
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } else {
            throw new RuntimeException("Image not found");
        }
    }
    @PostMapping("/menus")
    public Menu createMenu(@Valid @RequestBody Menu menu) {
        return menuRepository.save(menu);
    }

    @PutMapping("/menus/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable(value = "id") Integer menuId,
                                           @Valid @RequestBody Menu menuDetails) throws ResourceNotFoundException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + menuId));

        menu.setItem_name(menuDetails.getItem_name());
        menu.setPrice(menuDetails.getPrice());
        menu.setDescription(menuDetails.getDescription());
        menu.setImage(menuDetails.getImage());
        menu.setCategory(menuDetails.getCategory());

        final Menu updatedMenu = menuRepository.save(menu);
        return ResponseEntity.ok(updatedMenu);
    }

    @DeleteMapping("/menus/{id}")
    public Map<String, Boolean> deleteMenu(@PathVariable(value = "id") Integer menuId)
            throws ResourceNotFoundException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found for this id :: " + menuId));
        menuRepository.delete(menu);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
