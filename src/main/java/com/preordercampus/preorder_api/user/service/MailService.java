package com.preordercampus.preorder_api.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendIDVerifyMail(String email){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("[프리오더] 가입 이메일 인증 메일입니다.");
        simpleMailMessage.setText("[프리오더] 가입 이메일 인증 메일입니다. \n" +
                "다음 주소를 클릭하여 회원가입을 확정하세요 \n" +
                "본인의 계정이 아니라면 누르지 마세요 \n" +
                "http://localhost:8080/api/v1/user/verify/userEncodedIssuedCode");

        javaMailSender.send(simpleMailMessage);

    }

}
