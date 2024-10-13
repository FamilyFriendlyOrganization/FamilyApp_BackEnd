package com.familyapp.application.controller;

import com.familyapp.application.dto.AccountDto;
import com.familyapp.application.dto.UserDto;
import com.familyapp.application.entity.Family;
import com.familyapp.application.entity.Role;
import com.familyapp.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID userId){
        UserDto userDto = userService.getUserbyId(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtos = userService.getAllUser();
        return ResponseEntity.ok(userDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser( @RequestBody UserDto userDto,
                                                       @PathVariable("id") UUID userId){
        return ResponseEntity.ok(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") UUID userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
