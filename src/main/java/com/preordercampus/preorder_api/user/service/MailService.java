package com.preordercampus.preorder_api.user.service;

import com.preordercampus.preorder_api.user.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${server.url}")
    String url;

    public void sendIDVerifyMail(UserVO user){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("[프리오더] 가입 이메일 인증 메일입니다.");
        simpleMailMessage.setText("[프리오더] 가입 이메일 인증 메일입니다. \n" +
                "다음 주소를 클릭하여 회원가입을 확정하세요 \n" +
                "본인의 계정이 아니라면 누르지 마세요 \n" +
                url + "/api/v1/user/verify/" + user.getVerifyCode());

        javaMailSender.send(simpleMailMessage);
    }

}
