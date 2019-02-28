package com.dengcong.spring.webflux.example.controller;

import com.dengcong.spring.webflux.example.entity.User;
import com.dengcong.spring.webflux.example.repository.UserRepository;
import com.dengcong.spring.webflux.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;

/**
 * @author DC
 * @date 2019-02-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(value = "/block")
    public List<User> blocking() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/rx", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> rx() {
        return this.userService.findAll();
    }

    @GetMapping(value = "/rx2")
    public Mono<List<User>> rx2() {
        return this.userService.findAll2();
    }
}
