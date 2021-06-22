package com.sohee.layout.thlayout.validator;

import antlr.StringUtils;
import com.sohee.layout.thlayout.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Board board = (Board) obj; // 클래스로 형변환
        if (ObjectUtils.isEmpty(board.getContent())){ // StringUtils.isEmpty 대체
            errors.rejectValue("content", "key", "내용을 입력하세요.");
        }
    }
}
