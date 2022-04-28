package com.preordercampus.preorder_api.user.repository;

import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    AuthVO auth = new AuthVO("ROLE_USER");
    UserVO user = new UserVO("abc@naver.com",
            "123123",
            "STUDENT",
            true,
            "KAKAO",
            auth);


    @Test
    void findByEmail() throws NotFoundException {
        //given
        UserVO savedUser = userRepository.save(user);

        //when
        UserVO findMember =
                userRepository.findByEmail("abc@naver.com").orElseThrow(() -> new NotFoundException("invalid user email"));

        //then
        assertEquals("abc@naver.com", findMember.getEmail());
        assertEquals("123123", findMember.getPassword());
        assertEquals("STUDENT", findMember.getType());
        assertTrue(findMember.isActivated());
        assertEquals("KAKAO", findMember.getOauth());
    }

    @Test
    void existByEmail() {
        //given
        UserVO savedUser = userRepository.save(user);

        //when
        boolean isExist = userRepository.existsByEmail("abc@naver.com");

        //then
        assertTrue(isExist);
    }
}