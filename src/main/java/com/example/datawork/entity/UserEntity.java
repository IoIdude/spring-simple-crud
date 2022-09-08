package com.example.datawork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(nullable = false)
    private String nick;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "about_me", nullable = false)
    private String aboutMe;
    @Column(nullable = false)
    private String git;
    @Column(name = "work_hours", nullable = false)
    private Integer workHours;
}
