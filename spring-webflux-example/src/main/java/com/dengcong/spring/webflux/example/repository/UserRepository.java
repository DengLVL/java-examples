package com.dengcong.spring.webflux.example.repository;

import com.dengcong.spring.webflux.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DC
 * @date 2019-02-28
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
