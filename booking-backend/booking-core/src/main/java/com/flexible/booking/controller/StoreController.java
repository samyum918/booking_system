package com.flexible.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flexible.booking.dto.request.StoreRequest;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.Store;
import com.flexible.booking.repository.StoreRepository;
import com.flexible.booking.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cms/store")
public class StoreController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping("/create")
    public Store create(@Valid @RequestBody StoreRequest request) {
        return storeService.create(request);
    }

    @GetMapping("/get")
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @GetMapping("/get/{id:\\d+}")
    public Store getOne(@PathVariable Integer id) {
        return storeRepository.findById(id).orElseThrow(() -> new ApiResourceNotFoundException("Store does not exist."));
    }

    @PostMapping("/update")
    public Store update(@Valid @RequestBody StoreRequest request) {
        return storeService.update(request);
    }

    @PatchMapping("/delete/{id:\\d+}")
    public ObjectNode delete(@PathVariable Integer id) {
        storeRepository.softDeleteById(id);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", "SUCCESS");
        return objectNode;
    }
}
