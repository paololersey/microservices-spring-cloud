package com.example.demo.controller;


import com.example.demo.controller.model.UserDto;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<UserDto> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping
    public UserDto getUserById(@RequestParam(name="id") Long id){
        return this.userService.getUserById(id);

    }

    @PostMapping
    public UserDto addUser(@Validated @RequestBody UserDto userDto){
        return this.userService.addUser(userDto);
    }

    @PutMapping
    public UserDto updateUser(@Validated @RequestBody UserDto userDto){
        return this.userService.updateUser(userDto);
    }

    @DeleteMapping
    public boolean deleteUser(@RequestParam(name="id") Long id){
        return this.userService.deleteUser(id);
    }

    @GetMapping("/validate")
    public boolean validateUser(@RequestParam(name="id") Long id){
        return this.userService.validateUser(id);
    }
}
