package com.springboot.beginner.dto;

import com.springboot.beginner.entity.BoardEntity;

public class Board {
    private String title;
    private String content;

    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }
    @Override
    public String toString() {
        return "Board{" +
            "title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
    }

    public BoardEntity toEntity() {
        return new BoardEntity(null, title, content);
    }
}