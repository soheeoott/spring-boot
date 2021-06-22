package com.sohee.layout.thlayout.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}