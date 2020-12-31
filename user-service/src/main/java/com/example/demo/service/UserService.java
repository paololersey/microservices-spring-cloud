package com.example.demo.service;

import com.example.demo.controller.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    public List<UserDto> users = new ArrayList<>();

    public UserService(){
        UserDto user1= new UserDto(123L, "Peter", true);
        UserDto user2= new UserDto(342L, "Louise", true);
        users = new ArrayList<>(Arrays.asList(user1, user2));
    }

    public List<UserDto> getUsers(){
        return users;
    }

    public UserDto getUserById(@RequestParam(name="id") Long id){
        Optional<UserDto> userOptional = users.stream().filter(user -> user.getId() == id).findFirst();
        return userOptional.isPresent()? userOptional.get(): null;
    }

    public UserDto addUser(@Validated @RequestBody UserDto userDto){
        UserDto newUser= new UserDto(users.size() + 100L, userDto.getName(), true);
        users.add(newUser);
        return newUser;
    }

    public UserDto updateUser(@Validated @RequestBody UserDto userDto){
        Optional<UserDto> userOptional = users.stream().filter(user -> user.getId() == userDto.getId())
                .map(user -> {
                    user.setName(userDto.getName());
                    user.setActive(userDto.isActive());
                    return user;
                }).findFirst();
        return userOptional.isPresent()? userOptional.get(): null;
    }

    public boolean deleteUser(@RequestParam(name="id") Long id){
        final int originalSize = users.size();
        users = users.stream().filter(user -> user.getId() != id).collect(Collectors.toList());
        return originalSize> users.size();
    }

    public boolean validateUser(@RequestParam(name="id") Long id){
        Optional<UserDto> userOptional = users.stream().filter(user -> user.getId() == id).findFirst();
        return userOptional.isPresent()? userOptional.get().isActive(): false;
    }



}
