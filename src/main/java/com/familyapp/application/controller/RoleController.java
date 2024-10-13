package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.RoleDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/role")

public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto, Family family) {
        return new ResponseEntity<>(roleService.createRole(roleDto, family), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") UUID roleId){
        RoleDto roleDto = roleService.getRolebyId(roleId);
        return ResponseEntity.ok(roleDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRole(){
        List<RoleDto> roleDtos = roleService.getAllRole();
        return ResponseEntity.ok(roleDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDto> updateRole( @RequestBody RoleDto roleDto,
                                                       @PathVariable("id") UUID roleId){
        return ResponseEntity.ok(roleService.updateRole(roleId, roleDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") UUID roleId){
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
