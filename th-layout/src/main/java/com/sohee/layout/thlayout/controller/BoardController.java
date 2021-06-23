package com.sohee.layout.thlayout.controller;

import com.sohee.layout.thlayout.model.Board;
import com.sohee.layout.thlayout.repository.BoardRepository;
import com.sohee.layout.thlayout.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository; // 레파지토리로 테이블의 데이터를 가져옴

    @Autowired
    private BoardValidator boardValidator; // 커스텀 검증 클래스 선언

    @GetMapping("/list")
    public String list(Model model) {
        Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20)); // 데이터 전부 가져오기
        model.addAttribute("boards", boards); // 담은 데이터는 thymeleaf 에서 사용
        return "board/list";
    }

    @GetMapping("/write")
    public String write(Model model, @RequestParam(required = false) Long boardid) {
        if(boardid == null){
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(boardid).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/write";
    }

    @PostMapping("/write")
    public String writeSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult); // 첫 번째 파라미터 : 어떤 클래스를 체크할 것인지
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
