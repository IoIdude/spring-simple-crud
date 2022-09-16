package com.example.datawork.controller;

import com.example.datawork.entity.Country;
import com.example.datawork.entity.UserEntity;
import com.example.datawork.entity.WorkBook;
import com.example.datawork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String Start(UserEntity user, Model model) {
        Collection<Country> countries = service.allCountries();
        Collection<WorkBook> workBooks = service.allWorkBooks();
        model.addAttribute("country", countries);
        model.addAttribute("book", workBooks);
        return "reg";
    }

    @PostMapping("/reg")
    public String Add(@Valid UserEntity user, BindingResult result, @ModelAttribute("country") Country country, @ModelAttribute("book") WorkBook workBook, Model model) {
        if (!result.hasErrors()) {
            WorkBook book = service.findWorkBook(workBook.getNumber());
            if (service.getUserByNick(user.getNick()) == null && service.getUserByWorkBook(book) == null) {
                user.setRoles(new ArrayList<>());
                user.setCountry(service.findCountry(country.getCountryName()));
                user.setWorkBook(book);

                service.addUser(user);
                service.addRoleToUser(user.getNick(), "USER");

                return "redirect:/login";
            }
            else {
                Collection<Country> countries = service.allCountries();
                Collection<WorkBook> workBooks = service.allWorkBooks();
                model.addAttribute("country", countries);
                model.addAttribute("book", workBooks);
                model.addAttribute("userExist", true);
            }
        }

        return "reg";
    }

    @GetMapping("/main")
    public String preview(Model model) {
        List<UserEntity> users = service.getUsers();
        model.addAttribute("users", users);

        return "main";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") Integer id, UserEntity user, Model model) {
        UserEntity user_info = service.getUser(id);
        model.addAttribute("user", user_info);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@Valid UserEntity user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            service.editUser(user);
            return "redirect:/main";
        }

        model.addAttribute("user", user);

        return "edit";
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
        return "redirect:/main";
    }

    @GetMapping("filterExact")
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

        return "main";
    }

    @GetMapping("filterInaccurate")
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

        return "main";
    }
}
