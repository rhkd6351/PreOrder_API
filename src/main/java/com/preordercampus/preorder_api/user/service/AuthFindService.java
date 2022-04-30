package com.preordercampus.preorder_api.user.service;


import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthFindService {

    @Autowired
    AuthRepository authRepository;

    public AuthVO findByName(String name) {
        return authRepository.findById(name).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

}
