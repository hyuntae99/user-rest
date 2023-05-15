package io.hyunn.rest.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Getter
@Setter
@ToString
public class User {

    private String id;
    private String name;
    private String email;

    public User() {
        // 랜덤 아이디 생성
        this.id = UUID.randomUUID().toString();
    }

    public User(String name, String email) {
        // 위의 생성자를 통해 id를 랜덤을 생성한다.
        this();
        // 나머지 생성자
        this.name = name;
        this.email = email;
    }

    public static User sample() {
        return new User("Hyunn", "Hyunn@naver.com");
    }

    public static void main(String[] args) {
        User user = new User("Kim", "Kim@naver.com");
        System.out.println(new Gson().toJson(user)); // Gson 추가하고 json 형태로 출력

    }

}
