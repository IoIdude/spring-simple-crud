package com.example.datawork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "WorkBooks")
@Data
@NoArgsConstructor
public class WorkBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(unique = true)
    @Size(min = 7, max = 7, message = "Номер трудовой книжки меньше или больше 7 чисел")
    private String number;
    @OneToOne(mappedBy = "workBook")
    private UserEntity user;

    public WorkBook (Integer id, String number) {
        this.id = id;
        this.number = number;
    }
}
