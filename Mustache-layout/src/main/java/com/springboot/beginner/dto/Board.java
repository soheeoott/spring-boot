package com.springboot.beginner.dto;

import com.springboot.beginner.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Board {
    private String title;
    private String content;

    public BoardEntity toEntity() {
        return new BoardEntity(null, title, content);
    }
}