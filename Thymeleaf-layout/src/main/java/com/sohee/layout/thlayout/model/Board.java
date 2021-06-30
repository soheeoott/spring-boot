package com.sohee.layout.thlayout.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class Board {
    // 데이터베이스에서 정의한 필드들을 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardid;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자 이상 30자 이하입니다.")
    private String title;
    private String content;

    @ManyToOne // 게시글과 사용자와의 관계
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @JsonIgnore
    private User user;
}