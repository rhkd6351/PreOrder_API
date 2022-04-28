package com.preordercampus.preorder_api.user.service;

import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.repository.AuthRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthFindServiceTest {

    @Mock
    AuthRepository authRepository;

    @InjectMocks
    AuthFindService authFindService;

    @Test
    void findByName() throws NotFoundException {
        //given
        AuthVO authUser = new AuthVO("ROLE_USER");
        when(authRepository.findById("ROLE_USER")).thenReturn(
                Optional.of(authUser)
        );

        //when
        AuthVO auth = authFindService.findByName("ROLE_USER");

        //then
        assertEquals("ROLE_USER", authUser.getName());
    }
}