package io.hyunn.rest.service.logic;

import io.hyunn.rest.entity.User;
import io.hyunn.rest.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest // Test 클래스
public class UserServiceLogicTest {

    @Autowired // 테스트에서는 @Autowired 를 사용해야한다!
    private UserService userService;

    // 필드 선언
    private User Kim;
    private User Lee;


    @BeforeEach // 단위 Test 이전에 실행!
    // 초기화 작업
    public void startUp() {
        this.Kim = new User("Kim", "Kim@naver.com");
        this.Lee = new User("Lee", "Lee@naver.com");

        this.userService.register(Kim);
        this.userService.register(Lee);
    }


    // UserService의 로직이 잘 수행되는가를 테스트
    @Test // Test 메소드
    public void registerTest() {
        User sample = User.sample();

        // register(샘플)과 getId(샘플)이 같은가? -> 같아야 함
        assertThat(this.userService.register(sample)).isEqualTo(sample.getId());
        // 등록된 유저의 개수는 몇인가? 기존 2명 + 샘플 1명 = 3명
        assertThat(this.userService.findAll().size()).isEqualTo(3);
        // 샘플 삭제
        this.userService.remove(sample.getId());
    }

    @Test
    public void find() {
        // find(Lee의 ID)가 존재하는가? -> 존재해야 함
        assertThat(this.userService.find(Lee.getId())).isNotNull();
        // find(Lee의 ID)의 이메일과 Lee의 이메일이 같은가?
        assertThat(this.userService.find(Lee.getId()).getEmail()).isEqualTo(Lee.getEmail());
    }


    @AfterEach // 단위 Test 이후 실행!
    // 사용했던 값들 삭제
    public void cleanUp() {
        this.userService.remove(Kim.getId());
        this.userService.remove(Lee.getId());

    }


}
