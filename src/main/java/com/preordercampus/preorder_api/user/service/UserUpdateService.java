package com.preordercampus.preorder_api.user.service;


import com.preordercampus.preorder_api.user.domain.AuthVO;
import com.preordercampus.preorder_api.user.domain.UserVO;
import com.preordercampus.preorder_api.user.dto.CreateUser;
import com.preordercampus.preorder_api.user.repository.UserRepository;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserUpdateService {

    private final UserRepository userRepository;
    private final AuthFindService authFindService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public UserUpdateService(UserRepository userRepository, AuthFindService authFindService, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userRepository = userRepository;
        this.authFindService = authFindService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    @Transactional
    public Long saveStudentUser(CreateUser.Request request) throws NotFoundException, DuplicateMemberException {

        if(userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateMemberException("email duplicated");


        AuthVO auth = authFindService.findByName(AuthVO.Type.ROLE_USER.value());

        try{
            UserVO.Oauth.valueOf(request.getOauth());
        }catch (IllegalArgumentException e){
            throw new NotFoundException("invalid oauth name");
        }

        UserVO user = UserVO.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .activated(true)
                .auth(auth)
                .type(UserVO.Type.USER_STUDENT.value())
                .oauth(UserVO.Oauth.valueOf(request.getOauth()).value())
                .build();

        Long idx = userRepository.save(user).getIdx();
        mailService.sendIDVerifyMail(user.getEmail());

        return idx;
    }

}
