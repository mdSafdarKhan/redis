package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    @GetMapping("/{userId}")
    public User getUser(@PathVariable("userId") Long userId){
        System.out.println("Get user with ID: " + userId);
        return userRepository.findById(userId).get();
    }

    @CachePut(value = "users", key = "#userId")
    @PutMapping
    public User updateUser(@RequestBody User user){
        System.out.println(user);
        Optional<User> optUser = userRepository.findById(user.getUserId());
        if(optUser.isPresent()){
            User updatingUser = optUser.get();
            updatingUser.setFollowers(user.getFollowers());
            updatingUser.setName(user.getName());
            user = userRepository.save(updatingUser);
        }
        return user;
    }
}
