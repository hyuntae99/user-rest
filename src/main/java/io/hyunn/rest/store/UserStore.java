package io.hyunn.rest.store;

import io.hyunn.rest.entity.User;

import java.util.List;

public interface UserStore {

    String create(User newUser); // 새로운 유저 등록
    void update(User newUser); // 업데이트
    void delete(String id); // 삭제

    User retrieve(String id); // 참조
    List<User> retrieveAll(); // 모든 유저 참조

}
