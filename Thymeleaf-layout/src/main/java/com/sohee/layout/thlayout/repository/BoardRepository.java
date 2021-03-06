package com.sohee.layout.thlayout.repository;

import com.sohee.layout.thlayout.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> { // <모델 클래스, PK의 타입>
    // 구현을 하지 않아도 인터페이스를 정의하여 JpaRepository 를 상속받으면 데이터를 가져올 수 있음
    // 제목으로 검색하는 메소드
    List<Board> findByTitle(String title); // findByTitle : 검색할 필드명
    List<Board> findByTitleOrContent(String title, String content);

    // 게시글의 제목과 내용을 이용해서 검색
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
