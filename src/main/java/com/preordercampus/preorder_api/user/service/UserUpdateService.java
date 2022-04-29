package com.preordercampus.preorder_api.user.service;


import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.domain.SchoolVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.dto.CreateUser;
import com.preordercampus.preorder_api.user.repository.UserRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class UserUpdateService {

    private final UserRepository userRepository;

    private final AuthFindService authFindService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final SchoolFindService schoolFindService;

    public UserUpdateService(UserRepository userRepository, AuthFindService authFindService, PasswordEncoder passwordEncoder, MailService mailService, SchoolFindService schoolFindService) {
        this.userRepository = userRepository;
        this.authFindService = authFindService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.schoolFindService = schoolFindService;
    }

    @Transactional
    public Long saveStudentUser(CreateUser.Request request) throws NotFoundException, DuplicateMemberException {

        if(userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateMemberException("email duplicated");


        AuthVO auth = authFindService.findByName(AuthVO.Type.ROLE_USER.value());
        SchoolVO school = schoolFindService.findByIdx(request.getSchoolIdx());


        try{
            UserVO.Oauth.valueOf(request.getOauth());
        }catch (IllegalArgumentException e){
            throw new NotFoundException("invalid oauth name");
        }

        if(!request.getEmail().split("@")[1].equals(school.getDomain()))
            throw new IllegalArgumentException("email domain is not valid (only " + school.getDomain() + " allowed)");


        UserVO user = UserVO.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .activated(false)
                .auth(auth)
                .type(UserVO.Type.USER_STUDENT.value())
                .oauth(UserVO.Oauth.valueOf(request.getOauth()).value())
                .verifyCode(UUID.randomUUID().toString())
                .school(school)
                .build();

        Long idx = userRepository.save(user).getIdx();

        if(user.getOauth().equals("EMAIL"))
            mailService.sendIDVerifyMail(user);
        else
            throw new IllegalArgumentException("EMAIL 이외 버전은 아직 지원하지 않습니다.");

        return idx;
    }

    @Transactional
    public void verifyUserEmail(String code) throws NotFoundException {

        UserVO user = userRepository.findByVerifyCode(code).orElseThrow(() -> new NotFoundException("invalid user verify code"));
        user.activateUser();
    }

}
