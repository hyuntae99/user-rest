package io.hyunn.rest.store.logic;

import io.hyunn.rest.entity.User;
import io.hyunn.rest.store.UserStore;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository // Bean 객체로 등록하기 위한 어노테이션
public class UserStoreLogic implements UserStore {

    // id로 User 접근
    private Map<String, User> userMap;

    public UserStoreLogic() {
        this.userMap = new HashMap<>();
    }

    @Override
    public String create(User newUser) {
        this.userMap.put(newUser.getId(), newUser); // 새로운 ID로 유저 추가
        return newUser.getId();
    }

    @Override
    public void update(User newUser) {
        this.userMap.put(newUser.getId(), newUser); // 이미 있는 ID로 유저 업데이트
    }

    @Override
    public void delete(String id) {
        this.userMap.remove(id);
    }

    @Override
    public User retrieve(String id) {
        return this.userMap.get(id); // 유저 객체 반환
    }

    @Override
    public List<User> retrieveAll() {
        return this.userMap.values().stream().collect(Collectors.toList()); // 유저 객체들을 리스트로 반환
    }
}
