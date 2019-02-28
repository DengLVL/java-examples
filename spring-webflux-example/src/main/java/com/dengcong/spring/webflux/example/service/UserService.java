package com.dengcong.spring.webflux.example.service;

import com.dengcong.spring.webflux.example.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author DC
 * @date 2019-02-28
 */
public interface UserService {

    Flux<User> findAll();

    Mono<List<User>> findAll2();
}
