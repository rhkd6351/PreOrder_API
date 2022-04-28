package com.preordercampus.preorder_api.user.service;

import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.dto.CreateUser;
import com.preordercampus.preorder_api.user.repository.UserRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserUpdateServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthFindService authFindService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MailService mailService;

    @InjectMocks
    private UserUpdateService userUpdateService;

    @Test
    void saveStudentUser() throws NotFoundException, DuplicateMemberException {

        UserVO user =
                new UserVO("test@test.com", "password", "STUDENT", true, "KAKAO", "random code", null);
        user.setIdx(1L);

        //given
        when(userRepository.existsByEmail(anyString()))
                .thenReturn(false);
        when(userRepository.save(any()))
                .thenReturn(user);
        when(authFindService.findByName("ROLE_USER"))
                .thenReturn(new AuthVO("ROLE_USER"));
        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded password");


        CreateUser.Request request = CreateUser.Request.builder()
                .email("test@test.com")
                .password("password")
                .oauth("KAKAO")
                .build();

        //when
        Long idx = userUpdateService.saveStudentUser(request);

        //then
        assertEquals(1, idx);
    }

    @Test
    void verifyUserEmail() {
        //given

        //when

        //then
    }
}