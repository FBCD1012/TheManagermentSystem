package com.example.Controller.Advice;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class theExceptionController {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String getTheBadSqlGrammarException(){
        return "deleteError";
    }
}
