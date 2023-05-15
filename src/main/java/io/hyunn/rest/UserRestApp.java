package io.hyunn.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 루트에 써야 하는 종합적 어노테이션
public class UserRestApp {
    public static void main(String[] args) {
        // 런쳐
        SpringApplication.run(UserRestApp.class, args);
    }
}
