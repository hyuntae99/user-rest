package io.hyunn.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hyunn.rest.entity.User;
import io.hyunn.rest.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 자동완성이 안되면 구글링해서 수동으로 import 해야 함!
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

// 1번 방법
@SpringBootTest
@AutoConfigureMockMvc

// 2번 방법
// @WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired // 의존성 주입
    private MockMvc mockMVC; // 임의적으로 원하는 데이터를 POST, GET 등의 방식으로 보낼 수 있다.

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired // 테스트에서는 @Autowired 를 사용해야한다!
    private UserService userService;

    // 필드 선언
    private User Kim;
    private User Lee;

    @BeforeEach
    void setUp() {
        this.Kim = new User("Kim", "Kim@naver.com");
        this.Lee = new User("Lee", "Lee@naver.com");

        this.userService.register(Kim);
        this.userService.register(Lee);
    }

    @Test
    void register() throws Exception {
        User sample = User.sample(); // 유저 데이터 생성
        String content = objectMapper.writeValueAsString(sample); // JSON 형태로 변환
        mockMVC.perform(post("/users") // POST 방식으로 요청을 보냄
                .content(content) // JSON 데이터를 바디에 넣겠다.
                .contentType(MediaType.APPLICATION_JSON) // JSON 방식으로 보내겠다. (타입 지정)
                .accept(MediaType.APPLICATION_JSON)) // JSON 방식으로 보내겠다. (타입 지정)
                .andExpect(status().isOk()) // 200 OK 인지?
                .andExpect(content().string(sample.getId())) // 유저 ID와 동일한지?
                .andDo(print()); // 결과 전체 출력
        findAll(); // Kim, Lee -> Kim, Lee, Hyunn
    }

    @Test
    void find() throws Exception {
        String id = Kim.getId();
        String url = "/users/" + id;
        // Kim의 ID로 정보를 검색
        mockMVC.perform(get(url))
                .andExpect(status().isOk()) // 200 OK 인지?
                .andDo(print()); // 결과 전체 출력
    }

    @Test
    void findAll() throws Exception {
        mockMVC.perform(get("/users"))
                .andExpect(status().isOk()) // 200 OK 인지?
                .andDo(print()); // 결과 전체 출력
    }

    @Test
    void modify() throws Exception {
        // Jo의 User에 Lee의 아이디를 적용
        User Jo = new User("Jo","Jo@naver.com");
        Jo.setId(Lee.getId());
        // Lee를 JO로 변경
        String content = objectMapper.writeValueAsString(Jo); // JSON 형태로 변환
        mockMVC.perform(put("/users") // Put 방식으로 요청을 보냄
                        .content(content) // JSON 데이터를 바디에 넣겠다.
                        .contentType(MediaType.APPLICATION_JSON) // JSON 방식으로 보내겠다. (타입 지정)
                        .accept(MediaType.APPLICATION_JSON)) // JSON 방식으로 보내겠다. (타입 지정)
                .andExpect(status().isOk()) // 200 OK 인지?
                .andDo(print()); // 결과 전체 출력
        findAll(); // Kim, Lee -> Kim, Jo
    }

    @Test
    void remove() throws Exception {
        String id = Kim.getId();
        String url = "/users/" + id;
        // Kim의 ID로 삭제
        mockMVC.perform(delete(url))
                .andExpect(status().isOk()) // 200 OK 인지?
                .andDo(print()); // 결과 전체 출력
        findAll(); // Kim, Lee -> Lee
    }


    @AfterEach
    void tearDown() {
        this.userService.remove(Kim.getId());
        this.userService.remove(Lee.getId());
    }
}