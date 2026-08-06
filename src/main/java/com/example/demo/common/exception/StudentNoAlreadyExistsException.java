package com.example.demo.common.exception;

public class StudentNoAlreadyExistsException extends RuntimeException {
    public StudentNoAlreadyExistsException(String studentNumber) {
        super("学号" + studentNumber + "已存在");
    }
}
