package com.sohee.layout.thlayout.controller;

import com.sohee.layout.thlayout.model.User;
import com.sohee.layout.thlayout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class UserApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/user")
    User user(@RequestBody User newuser) {
        return repository.save(newuser);
    }

    @GetMapping("/user/{userid}")
    User one(@PathVariable Long userid) {
        return repository.findById(userid).orElse(null);
    }

    @PutMapping("/user/{userid}")
    User replaceuser(@RequestBody User newuser, @PathVariable Long userid) {

        return repository.findById(userid)
            .map(user -> {
//                user.setTitle(newuser.getTitle());
//                user.setContent(newuser.getContent());
                return repository.save(user);
            })
            .orElseGet(() -> {
//                newuser.setuserid(userid);
                return repository.save(newuser);
            });
    }

    @DeleteMapping("/user/{userid}")
    void deleteuser(@PathVariable Long userid) {
        repository.deleteById(userid);
    }
}