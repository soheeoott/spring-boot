package com.sohee.layout.thlayout.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    // 데이터베이스에서 정의한 필드들을 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String username;
    private String password;
    private Boolean enabled;

    @ManyToMany
    @JoinTable(
        name = "user_role", // 테이블 이름
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")) // 조인 할 상대 테이블
    private List<Role> roles = new ArrayList<>(); // 조인, nullPointException 방지

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // 사용자와 게시글의 관계, user 변수의 설정을 그대로 사용, 양방향 매핑 = 서로가 서로를 조회
    private List<Board> boards = new ArrayList<>();
}
