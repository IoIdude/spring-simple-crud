package com.example.datawork.service;

import com.example.datawork.entity.Country;
import com.example.datawork.entity.Role;
import com.example.datawork.entity.UserEntity;
import com.example.datawork.entity.WorkBook;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserEntity getUserByNick(String username);
    UserEntity addUser(UserEntity user);
    UserEntity getUser(Integer id);
    UserEntity getUserByWorkBook(WorkBook book);
    UserEntity editUser(UserEntity user);
    Role addRole(Role role);
    Country addCountry(Country country);
    Country findCountry(String name);
    Collection<Country> allCountries();
    WorkBook addWorkBook(WorkBook workBook);
    WorkBook findWorkBook(String number);
    Collection<WorkBook> allWorkBooks();
    void addRoleToUser(String username, String roleName);
    void deleteUser(Integer id);
    List<UserEntity> getUsers();
    List<UserEntity> getUsersByFilterUse(String tag);
    List<UserEntity> getUsersByFilterUse(Integer tag);
    List<UserEntity> getUsersLikeByFilterUse(String tag);
    List<UserEntity> getUsersLikeByFilterUse(Integer tag);
}
