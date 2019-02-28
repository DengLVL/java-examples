package com.dengcong.spring.webflux.example.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author DC
 * @date 2019-02-28
 */
@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
