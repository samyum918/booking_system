package com.flexible.booking.controller;

import com.flexible.booking.dto.request.CreateStoreRequest;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.Store;
import com.flexible.booking.repository.StoreRepository;
import com.flexible.booking.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/cms/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @PostMapping("/create")
    public Store create(@Valid @RequestBody CreateStoreRequest request) {
        return storeService.create(request);
    }

    @PatchMapping("/inactivate/{id:\\d+}")
    public Store inactivate(@PathVariable Integer id) {
        Optional<Store> storeOpt = storeRepository.findById(id);
        if(!storeOpt.isPresent()) {
            throw new ApiResourceNotFoundException("Store does not exist.");
        }
        Store store = storeOpt.get();
        store.setCanBook(false);
        return storeRepository.save(store);
    }
}
