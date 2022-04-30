package com.preordercampus.preorder_api.user.service;

import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.repository.UserRepository;
import com.preordercampus.preorder_api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserFindService {

    private final UserRepository userRepository;


    public UserFindService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserVO getMyUserWithAuthorities(){
        Optional<UserVO> memberVO = SecurityUtil.getCurrentUsername().flatMap(userRepository::findByEmail);

        if(memberVO.isEmpty())
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);

        return memberVO.get();
    }
}
