package com.example.blogappapis.services;

import com.example.blogappapis.entities.User;
import com.example.blogappapis.exception.ResourceNotFoundException;
import com.example.blogappapis.payloads.UserDto;
import com.example.blogappapis.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto dtoUser) {
        User createdUser = this.modelMapper.map(dtoUser,User.class);
        User userSaved = this.userRepo.save(createdUser);
        return this.modelMapper.map(userSaved,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto dtoUser, Integer userId) {
        User oldUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        oldUser.setName(dtoUser.getName());
        oldUser.setEmail(dtoUser.getEmail());
        oldUser.setAbout(dtoUser.getAbout());
        oldUser.setPassword(dtoUser.getPassword());
        User updatedUser = this.userRepo.save(oldUser);
        return this.modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User getUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        return this.modelMapper.map(getUser,UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = this.userRepo.findAll();
        List<UserDto> userDtos = allUsers.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User toDeleteUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
        this.userRepo.delete(toDeleteUser);
    }
}
