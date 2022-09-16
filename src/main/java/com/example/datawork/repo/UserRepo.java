package com.example.datawork.repo;

import com.example.datawork.entity.UserEntity;
import com.example.datawork.entity.WorkBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByNickOrGitOrAboutMe(String nick, String git, String about_me);
    List<UserEntity> findByAgeOrWorkHoursOrId(Integer age, Integer work_hours, Integer number);

    List<UserEntity> findByAgeGreaterThanEqualOrWorkHoursGreaterThanEqualOrIdGreaterThanEqual(Integer age, Integer work_hours, Integer number);
    List<UserEntity> findByNickContainingOrGitContainingOrAboutMeContaining(String nick, String git, String about_me);

    UserEntity findByNick(String username);
    UserEntity findByWorkBook(WorkBook book);
}
