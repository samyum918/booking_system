package com.flexible.booking.controller;

import com.flexible.booking.dto.request.CreateStoreRequest;
import com.flexible.booking.exception.ApiBadRequestException;
import com.flexible.booking.exception.ApiResourceNotFoundException;
import com.flexible.booking.model.Store;
import com.flexible.booking.repository.StoreRepository;
import com.flexible.booking.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping("/get")
    public List<Store> getAll(@RequestParam(defaultValue = "1", required = false) Integer page,
                              @RequestParam(defaultValue = "10", required = false) Integer limit) {
        if(page < 1 || limit < 1) {
            throw new ApiBadRequestException("Page and limit must be greater than or equal to 1");
        }

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Store> storesList = storeRepository.findAll(pageable);
        return storesList.getContent();
    }

    @GetMapping("/get/{id:\\d+}")
    public Store getOne(@PathVariable Integer id) {
        return storeRepository.findById(id).orElseThrow(() -> new ApiResourceNotFoundException("Store does not exist."));
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
