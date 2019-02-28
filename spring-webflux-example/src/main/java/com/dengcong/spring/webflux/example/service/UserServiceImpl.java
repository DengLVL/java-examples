package com.dengcong.spring.webflux.example.service;

import com.dengcong.spring.webflux.example.entity.User;
import com.dengcong.spring.webflux.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author DC
 * @date 2019-02-28
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Scheduler jdbcScheduler;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Qualifier("jdbcScheduler") Scheduler jdbcScheduler) {
        this.userRepository = userRepository;
        this.jdbcScheduler = jdbcScheduler;
    }

    private <T> Mono asyncCallable(Callable callable){
        return Mono.fromCallable(callable).publishOn(jdbcScheduler);
    }

    @Override
    public Flux<User> findAll() {
        return asyncIterable(userRepository.findAll());
    }

    private <T> Flux<T> asyncIterable(Iterable<T> it) {
        return Flux.fromIterable(it).publishOn(this.jdbcScheduler);
    }

    @Override
    public Mono<List<User>> findAll2() {
        return asyncCallable(userRepository::findAll);
    }
}
