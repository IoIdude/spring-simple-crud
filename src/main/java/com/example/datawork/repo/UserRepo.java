package com.example.datawork.repo;

import com.example.datawork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByNickOrGitOrAboutMe(String nick, String git, String about_me);
    List<UserEntity> findByAgeOrWorkHoursOrId(Integer age, Integer work_hours, Integer number);

    List<UserEntity> findByAgeGreaterThanEqualOrWorkHoursGreaterThanEqualOrIdGreaterThanEqual(Integer age, Integer work_hours, Integer number);
    List<UserEntity> findByNickContainingOrGitContainingOrAboutMeContaining(String nick, String git, String about_me);
}