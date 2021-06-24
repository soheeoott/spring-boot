package com.sohee.layout.thlayout.repository;

import com.sohee.layout.thlayout.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 구현을 하지 않아도 인터페이스를 정의하여 JpaRepository 를 상속받으면 데이터를 가져올 수 있음
    User findByUsername(String username); // 검색, 컬럼이름과 일치하는 사용자 데이터를 가져옴
}