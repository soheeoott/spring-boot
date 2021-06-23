package com.sohee.layout.thlayout.controller;

import com.sohee.layout.thlayout.model.Board;
import com.sohee.layout.thlayout.repository.BoardRepository;
import com.sohee.layout.thlayout.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable, 
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        // Page<Board> boards = boardRepository.findAll(pageable); // 데이터 전부 가져오기
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable); // 게시글의 제목과 내용을 이용해서 검색
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4); // 현재 페이지에서 첫 번째 페이지를 계산, 최소값으로 1을 설정
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4); // 현재 페이지에서 마지막 페이지를 계산, 최대값은 총 페이지의 개수
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
