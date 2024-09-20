package com.sboard.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class MailSendService {
    @Autowired
    private JavaMailSender mailSender;
    private int authNumber;

    public void makeRandomNumber(){
        // 인증코드 생성
        authNumber= ThreadLocalRandom.current().nextInt(100000, 1000000);
    }
    public String joinEmail(String email){
        makeRandomNumber();
        String setFrom = "noily4748@gmail.com";
        String toMail = email;
        String title = "Sboard 회원 가입 인증 이메일입니다.";
        String content = "<h1>인증코드는 " + authNumber + "입니다.</h1>";
        mailSend(setFrom, toMail, title, content);

        return Integer.toString(authNumber);
    }
    public void mailSend(String setFrom, String toMail, String title, String content){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(mimeMessage);

        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
