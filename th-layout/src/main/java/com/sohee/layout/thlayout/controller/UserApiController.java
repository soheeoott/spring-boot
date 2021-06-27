package com.sohee.layout.thlayout.controller;

import com.sohee.layout.thlayout.model.Board;
import com.sohee.layout.thlayout.model.User;
import com.sohee.layout.thlayout.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
class UserApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    List<User> all() {
        List<User> users = repository.findAll();
        log.debug("getBoards().size() 호출전");
        log.debug("getBoards().size() : {}", users.get(0).getBoards().size());
        log.debug("getBoards().size() 호출후");
        return users;
    }

    @PostMapping("/user")
    User user(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/user/{userid}")
    User one(@PathVariable Long userid) {
        return repository.findById(userid).orElse(null);
    }

    @PutMapping("/user/{userid}")
    User replaceuser(@RequestBody User newUser, @PathVariable Long userid) {

        return repository.findById(userid)
            .map(user -> {
//                user.setTitle(newuser.getTitle());
//                user.setContent(newuser.getContent());
//                user.setBoards(newUser.getBoards());
                user.getBoards().clear();
                user.getBoards().addAll(newUser.getBoards());
                for(Board board : user.getBoards()){
                    board.setUser(user);
                }
                return repository.save(user);
            })
            .orElseGet(() -> {
//                newuser.setuserid(userid);
                return repository.save(newUser);
            });
    }

    @DeleteMapping("/user/{userid}")
    void deleteuser(@PathVariable Long userid) {
        repository.deleteById(userid);
    }
}