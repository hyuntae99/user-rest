package io.hyunn.rest.controller;

import io.hyunn.rest.entity.User;
import io.hyunn.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController = 결과를 응답으로 내보낼 때 JSON 형태로 보낸다!
@RestController
// 클라이언트 <-> 프록시 서버 <-> 오리진 서버
// 클라이언트는 프록시 서버를 통해서 원하는 데이터를 주고 받고 오리진 서버에 직접 접근 X
// 오리진 서버는 프록시 서버와 JSON 형태로 주고 받음.
// 위의 기능을 구현해주는 것이 Spring 의 RestController 이다!
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // 의존성 주입

    @PostMapping("/users") // POST 방식으로 데이터 받아오기 -> Body에 담겨서 데이터가 온다.
    public String register(@RequestBody User newUser) {
        return userService.register(newUser);
    }

    @GetMapping("/users/{id}") // GET 방식으로 id를 받아온다
    public User find(@PathVariable String id) {
        return userService.find(id);
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PutMapping("/users")
    public void modify(@RequestBody User newUser) {
        userService.modify(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void remove(@PathVariable String id) {
        userService.remove(id);
    }

}
