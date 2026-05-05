package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
        //启动时打印信息
        System.out.println("启动成功");
        System.out.println("\n________________________");
        System.out.println("🌏访问网址：http://localhost:8080");
    }
}
