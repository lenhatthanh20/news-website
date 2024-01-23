package com.lenhatthanh.blog.modules.user.infrastructure.rest_api;

import com.lenhatthanh.blog.modules.user.application.usecase.CreateRoleUseCase;
import com.lenhatthanh.blog.modules.user.application.usecase.UpdateRoleUseCase;
import com.lenhatthanh.blog.modules.user.dto.RoleDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private CreateRoleUseCase createRoleUseCase;
    private UpdateRoleUseCase updateRoleUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createRole(@RequestBody RoleDto role) {
        createRoleUseCase.execute(role);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateRole(@RequestBody RoleDto role) {
        updateRoleUseCase.execute(role);
    }
}
