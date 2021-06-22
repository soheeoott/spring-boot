package com.sohee.layout.thlayout.controller;

import com.sohee.layout.thlayout.model.Board;
import com.sohee.layout.thlayout.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository repository;

    @GetMapping("/board")
    List<Board> all() {
        return repository.findAll();
    }

    @PostMapping("/board")
    Board board(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    @GetMapping("/board/{boardid}")
    Board one(@PathVariable Long boardid) {
        return repository.findById(boardid).orElse(null);
    }

    @PutMapping("/board/{boardid}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long boardid) {

        return repository.findById(boardid)
            .map(Board -> {
                Board.setTitle(newBoard.getTitle());
                Board.setContent(newBoard.getContent());
                return repository.save(Board);
            })
            .orElseGet(() -> {
                newBoard.setBoardid(boardid);
                return repository.save(newBoard);
            });
    }

    @DeleteMapping("/board/{boardid}")
    void deleteBoard(@PathVariable Long boardid) {
        repository.deleteById(boardid);
    }
}