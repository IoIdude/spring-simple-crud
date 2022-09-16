package com.example.datawork.repo;

import com.example.datawork.entity.WorkBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkBookRepo extends JpaRepository<WorkBook, Integer> {
    WorkBook findByNumber(String number);
}
