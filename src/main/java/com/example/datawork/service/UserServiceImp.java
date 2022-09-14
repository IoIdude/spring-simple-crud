package com.example.datawork.service;

import com.example.datawork.entity.Role;
import com.example.datawork.entity.UserEntity;
import com.example.datawork.repo.RoleRepository;
import com.example.datawork.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo repository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserEntity user = repository.findByNick(username);
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public UserEntity getUserByNick(String username) {
        return repository.findByNick(username);
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
    public List<UserEntity> getUsersByFilterUse(String tag) {
        return repository.findByNickOrGitOrAboutMe(tag, tag, tag);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByNick(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getNick(), user.getPassword(), authorities);
    }
}
