package com.example.datawork.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "Countries")
@Data
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(unique = true)
    private String countryName;
    @OneToMany(mappedBy = "country", fetch = EAGER)
    private Collection<UserEntity> user;

    public Country(Integer id, String country) {
        this.id = id;
        this.countryName = country;
    }
}
