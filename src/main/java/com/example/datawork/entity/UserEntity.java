package com.example.datawork.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, max = 30, message = "Имя должно быть больше 3 и меньше 30 символов")
    private String nick;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @NotNull
    @Min(value = 18, message = "Возраст должен быть больше 17")
    @Max(value = 80, message = "Возраст должен быть не больше 80")
    private Integer age;
    @Column(name = "about_me", nullable = false)
    @NotEmpty
    @Size(min = 10, max = 200, message = "Информация о вас должна быть не меньше 10 символов и не больше 200")
    private String aboutMe;
    @Column(nullable = false)
    @NotEmpty
    @Pattern(regexp = "^(http|https).*", message = "Ссылка должна содержать корректный протокол (http/https)")
    @Size(min = 15, max = 200, message = "Ссылка не должна быть меньше 15 символов и больше 200")
    private String git;
    @Column(name = "work_hours", nullable = false)
    @NotNull
    @Min(value = 1, message = "Часы работы не должны быть меньше 1 часу")
    @Max(value = 24, message = "Часы работы не должны быть больше 24 часов")
    private Integer workHours;
    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_book_id")
    private WorkBook workBook;
    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;
}
