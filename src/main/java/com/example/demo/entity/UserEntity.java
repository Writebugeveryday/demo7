package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

/**
 * 实体类，对应数据库
 */
@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "username")
    private String username;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;
}
