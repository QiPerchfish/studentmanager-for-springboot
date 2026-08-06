package com.example.demo.common.DTO;

import jakarta.validation.constraints.*;

public class StudentRequest {
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 20, message = "姓名必须达到2-20个字")
    private String name;

    @NotBlank(message = "学号不能为空")
    @Size(min = 1, max = 1000, message = "学号必须达到1-1000个数字(最小1-10)")
    private String number;

    @NotBlank(message = "年龄不能为空")
    @Min(value = 5, message = "年龄最小是5，不能超过5")
    @Max(value = 100, message = "年龄最大是100，不能超过100")
    private Integer age;

    // 全参数构造方法
    public StudentRequest(String name, String number, Integer age) {
        this.name = name;
        this.number = number;
        this.age = age;
    }

    // getter与setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
