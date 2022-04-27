package com.preordercampus.preorder_api.user.service;


import com.limbae.preorder.anabada_api.user.domain.UserVO;
import com.limbae.preorder.anabada_api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {

    private UserRepository userRepository;

    public Long save(UserVO user){
        return 1L;
    }

}
