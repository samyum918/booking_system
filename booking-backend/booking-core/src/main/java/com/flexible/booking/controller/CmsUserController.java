package com.flexible.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flexible.booking.dto.request.RegisterCmsUserRequest;
import com.flexible.booking.model.CmsUser;
import com.flexible.booking.repository.CmsUserRepository;
import com.flexible.booking.service.CmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cms/user")
public class CmsUserController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CmsUserService cmsUserService;

    @Autowired
    private CmsUserRepository cmsUserRepository;

    @PostMapping("/register")
    public CmsUser register(@Valid @RequestBody RegisterCmsUserRequest request) {
        return cmsUserService.register(request);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public ObjectNode delete(@PathVariable Integer id) {
        cmsUserRepository.deleteById(id);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", "SUCCESS");
        return objectNode;
    }
}
