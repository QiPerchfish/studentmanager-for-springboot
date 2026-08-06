package com.example.demo.common.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("学生" + id + "不存在");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
