package com.example.spring_project1.controller;

import com.example.spring_project1.dao.UserDao;
import com.example.spring_project1.model.User;
import com.example.spring_project1.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService service;
    private final UserDao userDao;

    public UserController(UserService service, UserDao userDao) {
        this.service = service;
        this.userDao     = userDao;
    }

    @GetMapping("/jdbc")
    public List<User> getAllJdbc() {
        return userDao.findAll();
    }

    @PostMapping("/jdbc")
    public String createJdbc(@RequestBody User user) {
        userDao.create(user);
        return "Created via JDBC: " + user.getName();
    }


    //JPA end points
    @GetMapping("/jpa")
    public List<User> getUsers() {
        return service.getAllUsers();
    }
    @GetMapping("/jpa/{name}")
    public User getUserById(@PathVariable String name) {
        return service.getUserByName(name);
    }
    @PostMapping("/jpa")
    public User createUser(@RequestBody  User user) {
        return service.CreateUser(user);
    }
    @PutMapping("/jpa/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return service.updateUser(id, user);
    }
    @DeleteMapping ("/jpa/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }

}
