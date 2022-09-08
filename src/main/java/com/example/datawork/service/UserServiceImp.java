package com.example.datawork.service;

import com.example.datawork.entity.UserEntity;
import com.example.datawork.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepo repository;

    @Override
    public UserEntity addUser(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public List<UserEntity> getUsers() {
        return repository.findAll();
    }

    @Override
    public UserEntity getUser(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UserEntity editUser(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<UserEntity> getUsersByFilterUse(Integer tag) {
        return repository.findByAgeOrWorkHoursOrId(tag, tag, tag);
    }

    @Override
    public List<UserEntity> getUsersLikeByFilterUse(String tag) {
        return repository.findByNickContainingOrGitContainingOrAboutMeContaining(tag, tag, tag);
    }

    @Override
    public List<UserEntity> getUsersLikeByFilterUse(Integer tag) {
        return repository.findByAgeGreaterThanEqualOrWorkHoursGreaterThanEqualOrIdGreaterThanEqual(tag, tag, tag);
    }

    @Override
    public List<UserEntity> getUsersByFilterUse(String tag) {
        return repository.findByNickOrGitOrAboutMe(tag, tag, tag);
    }
}
