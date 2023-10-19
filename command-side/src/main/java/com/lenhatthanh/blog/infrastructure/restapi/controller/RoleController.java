package com.lenhatthanh.blog.infrastructure.restapi.controller;

import com.lenhatthanh.blog.application.usecase.CreateRole;
import com.lenhatthanh.blog.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private CreateRole createRoleUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRole(@RequestBody RoleDto role) {
        createRoleUseCase.execute(role);
    }
}
