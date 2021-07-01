package com.springboot.beginner.controller;

import com.springboot.beginner.dto.Board;
import com.springboot.beginner.entity.BoardEntity;
import com.springboot.beginner.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class CrudController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @PostMapping("/create")
    public String create(Board board) {
        // System.out.println(board.toString()); -> 로깅으로
        log.info(board.toString());

        BoardEntity boardEntity = board.toEntity();
        // System.out.println(boardEntity.toString()); -> 로깅으로
        log.info(boardEntity.toString());

        BoardEntity saved = boardRepository.save(boardEntity);
        // System.out.println(saved.toString()); -> 로깅으로
        log.info(saved.toString());

        return "";
    }
}
