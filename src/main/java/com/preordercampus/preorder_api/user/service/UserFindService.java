package com.preordercampus.preorder_api.user.service;

import com.preordercampus.preorder_api.exception.ApiException;
import com.preordercampus.preorder_api.exception.ExceptionEnum;
import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.repository.UserRepository;
import com.preordercampus.preorder_api.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserFindService {

    private final UserRepository userRepository;


    public UserFindService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserVO getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findByEmail)
                .orElseThrow(
                        () -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION)
                );
    }

    @Transactional(readOnly = true)
    public UserVO findByIdx(Long idx) {
        return userRepository.findById(idx).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_EXCEPTION)
        );
    }

    @Transactional(readOnly = true)
    public List<UserVO> findAll() {
        return userRepository.findAll();
    }


}
