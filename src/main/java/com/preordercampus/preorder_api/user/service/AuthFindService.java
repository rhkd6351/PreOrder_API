package com.preordercampus.preorder_api.user.service;


import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.repository.AuthRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthFindService {

    @Autowired
    AuthRepository authRepository;

    public AuthVO findByName(String name) throws NotFoundException {
        return authRepository.findById(name).orElseThrow(
                () -> new NotFoundException("Invalid Auth Name")
        );
    }

}
