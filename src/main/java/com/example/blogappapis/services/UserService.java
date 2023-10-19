package com.example.blogappapis.services;

import com.example.blogappapis.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserDto createUser (UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
