package com.example.demo.common.DTO;

public class StudentResponse {
    private Long id;
    private String name;
    private Integer age;
    private String number;

    public StudentResponse() {}

    public StudentResponse(Long id, String name, Integer age, String number) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.number = number;
    }

    // getter/setter

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
