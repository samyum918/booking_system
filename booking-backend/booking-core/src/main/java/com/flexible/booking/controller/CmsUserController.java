package com.flexible.booking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flexible.booking.dto.request.RegisterCmsUserRequest;
import com.flexible.booking.exception.ApiForbiddenException;
import com.flexible.booking.model.CmsUser;
import com.flexible.booking.repository.CmsUserRepository;
import com.flexible.booking.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/user")
public class CmsUserController {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CmsUserRepository cmsUserRepository;

    @PostMapping("/register")
    public CmsUser register(RegisterCmsUserRequest request) {
        Integer existingRecords = cmsUserRepository.countByUsername(request.getUsername());
        if(existingRecords > 0) {
            throw new ApiForbiddenException("CMS User already exists.");
        }

        CmsUser cmsUser = ProjectUtils.transformFrom(request, CmsUser.class);
        return cmsUserRepository.save(cmsUser);
    }

    @DeleteMapping("/delete/{id:\\d+}")
    public ObjectNode delete(@PathVariable Integer id) {
        cmsUserRepository.deleteById(id);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("status", "SUCCESS");
        return objectNode;
    }
}
