package com.sohee.layout.thlayout.service;

import com.sohee.layout.thlayout.model.Board;
import com.sohee.layout.thlayout.model.User;
import com.sohee.layout.thlayout.repository.BoardRepository;
import com.sohee.layout.thlayout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board) {
        // username 을 바탕으로 userid 를 조회
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
