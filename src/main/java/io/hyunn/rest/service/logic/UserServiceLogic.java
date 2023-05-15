package io.hyunn.rest.service.logic;

import io.hyunn.rest.entity.User;
import io.hyunn.rest.service.UserService;
import io.hyunn.rest.store.UserStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Bean으로 등록
@RequiredArgsConstructor // 1. lombok을 통한 주입
public class UserServiceLogic implements UserService {

    // final을 사용했기 때문에 생성자를 통해서 반드시 초기화가 필요하다.
    // @RequiredArgsConstructor 어노테이션에서 자동으로 생성자를 만들어준다.
    private final UserStore userStore;

    // 2. UserStore를 구성하고 있는 로직을 주입한다.
    // @Autowired
    // private UserStore userStore;

    // 3. 생성자를 통해서 userStore를 주입할 수 있다.
    // private UserStore userStore;
    // public UserServiceLogic(UserStore userStore) {
    //     this.userStore = userStore;
    // }


    // UserStore 로직을 활용하여 UserService 로직을 구현
    @Override
    public String register(User newUser) {
        return userStore.create(newUser);
    }

    @Override
    public void modify(User newUser) {
        this.userStore.update(newUser);
    }

    @Override
    public void remove(String id) {
        this.userStore.delete(id);
    }

    @Override
    public User find(String id) {
        return this.userStore.retrieve(id);
    }

    @Override
    public List<User> findAll() {
        return this.userStore.retrieveAll();
    }
}
