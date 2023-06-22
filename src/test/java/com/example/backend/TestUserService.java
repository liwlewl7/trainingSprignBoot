package com.example.backend;

import com.example.backend.Entity.Address;
import com.example.backend.Entity.Social;
import com.example.backend.Entity.User;
import com.example.backend.exception.BaseException;
import com.example.backend.service.AddressService;
import com.example.backend.service.SocialService;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;
    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

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
    void testCreateSocail() throws BaseException {
        Optional<User> opt = userService.findByEmail(TestUpdateData.email);
        Assertions.assertTrue(opt.isPresent());


        User user = opt.get();
        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(user,
                SocialTestCreate.facebook,
                SocialTestCreate.Line,
                SocialTestCreate.instagram,
                SocialTestCreate.tiktok);


        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreate.facebook, social.getFacebook());

    }

    @Order(3)
    @Test
    void testCreateAddress() {
        Optional<User> opt = userService.findByEmail(TestUpdateData.email);
        Assertions.assertTrue(opt.isPresent());


        User user = opt.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

        createAddress(user, AddressTestCreate.line1, AddressTestCreate.line2, AddressTestCreate.zipcode);
        createAddress(user, AddressTestCreate2.line1, AddressTestCreate2.line2, AddressTestCreate2.zipcode);
    }

    private void createAddress(User user, String line1, String line2, String zipcode) {
        Address address = addressService.create(user, line1, line2, zipcode);

        //validate
        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1, address.getLine1());
        Assertions.assertEquals(line2, address.getLine2());
        Assertions.assertEquals(zipcode, address.getZipcode());
    }

    @Order(9)
    @Test
    void testDelete() {
        Optional<User> opt = userService.findByEmail(TestUpdateData.email);
        Assertions.assertTrue(opt.isPresent());
        User user = opt.get();

        // check social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(SocialTestCreate.facebook, social.getFacebook());
        // check address
        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2, addresses.size());

        //delete
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

    interface SocialTestCreate {
        String facebook = "whoami";
        String Line = "";
        String instagram = "";
        String tiktok = "";

    }

    interface AddressTestCreate {
        String line1 = "704/232";
        String line2 = "bkk";
        String zipcode = "11111";
    }

    interface AddressTestCreate2 {
        String line1 = "1/22";
        String line2 = "pk";
        String zipcode = "123";
    }

}
