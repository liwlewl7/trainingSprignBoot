package com.example.backend;

import com.example.backend.Entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.create(
                TestCreateData.email,
                TestCreateData.password,
                TestCreateData.name
        );
        //check notnull
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        //check equals
        Assertions.assertEquals(TestCreateData.email, user.getEmail());
        Assertions.assertTrue(userService.matchPassword(TestCreateData.password, user.getPassword()));
        Assertions.assertEquals(TestCreateData.name, user.getName());
    }

    @Order(2)
    @Test
    void testUpdate() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestUpdateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        User updateUser = userService.updateName(user.getId(), TestUpdateData.name);
        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals(updateUser.getName(), TestUpdateData.name);

    }

    @Order(3)
    @Test
    void testDelete() {
        Optional<User> opt = userService.findByEmail(TestUpdateData.email);
        Assertions.assertTrue(opt.isPresent());

        User user = opt.get();
        userService.deleteById(user.getId());
        Optional<User> optDelte = userService.findByEmail(TestCreateData.email);
        Assertions.assertTrue(optDelte.isEmpty());
    }

    interface TestCreateData {
        String email = "liwlew@super.com";
        String password = "lovedota2";
        String name = "SLiw";
    }

    interface TestUpdateData {
        String email = "liwlew@super.com";
        String password = "lovedota2";
        String name = "LG";
    }

}
