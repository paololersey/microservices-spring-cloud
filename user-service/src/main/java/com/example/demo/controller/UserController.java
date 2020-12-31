package com.example.demo.controller;


import com.example.demo.controller.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping("/all")
    public List<UserDto> getUsers(){
        return new ArrayList<UserDto>();
    }

    @GetMapping
    public UserDto getUsers(@RequestParam(name="id") Long id){
        return new UserDto();
    }

    @PostMapping
    public UserDto addUser(@Validated @RequestBody UserDto userDto){
        return new UserDto();
    }

    @PutMapping
    public UserDto updateUser(@Validated @RequestBody UserDto userDto){
        return new UserDto();
    }

    @DeleteMapping
    public boolean deleteUser(@RequestParam(name="id") Long id){
        return true;
    }

    @GetMapping("/validate")
    public boolean validateUser(@RequestParam(name="id") Long id){
        return true;
    }
}
