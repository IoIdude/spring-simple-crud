package com.example.datawork.controller;

import com.example.datawork.entity.UserEntity;
import com.example.datawork.repo.UserRepo;
import com.example.datawork.service.UserService;
import com.example.datawork.service.UserServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String Start(Model model) {
        List<UserEntity> users = service.getUsers();
        model.addAttribute("users", users);

        return "create";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        UserEntity user = service.getUser(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") UserEntity user) {
        service.editUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        UserEntity user = service.getUser(id);
        model.addAttribute("user", user);
        return "preview";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        service.deleteUser(id);
        return "redirect:/";
    }

    @PostMapping("/")
    public String Add(@ModelAttribute("user") UserEntity user, Model model) {
        service.addUser(user);

        List<UserEntity> users = service.getUsers();
        model.addAttribute("users", users);

        return "create";
    }

    @PostMapping("filterExact")
    public String FilterExact(@RequestParam(value = "filter", required = false) String filter, Model model) {
        List<UserEntity> users;

        if (filter != null && !filter.isEmpty()) {
            try {
                Integer num = Integer.parseInt(filter);
                users = service.getUsersByFilterUse(num);
            } catch (NumberFormatException e) {
                users = service.getUsersByFilterUse(filter);
            }
        }
        else {
            users = service.getUsers();
        }
        model.addAttribute("users", users);

        return "create";
    }

    @PostMapping("filterInaccurate")
    public String FilterInaccurate(@RequestParam(value = "filter", required = false) String filter, Model model) {
        List<UserEntity> users;

        if (filter != null && !filter.isEmpty()) {
            try {
                Integer num = Integer.parseInt(filter);
                users = service.getUsersLikeByFilterUse(num);
            } catch (NumberFormatException e) {
                users = service.getUsersLikeByFilterUse(filter);
            }
        }
        else {
            users = service.getUsers();
        }
        model.addAttribute("users", users);

        return "create";
    }
}
