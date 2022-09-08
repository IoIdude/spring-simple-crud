package com.example.datawork.service;

import com.example.datawork.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserEntity addUser(UserEntity user);
    UserEntity getUser(Integer id);
    UserEntity editUser(UserEntity user);
    void deleteUser(Integer id);
    List<UserEntity> getUsers();
    List<UserEntity> getUsersByFilterUse(String tag);
    List<UserEntity> getUsersByFilterUse(Integer tag);
    List<UserEntity> getUsersLikeByFilterUse(String tag);
    List<UserEntity> getUsersLikeByFilterUse(Integer tag);
}
