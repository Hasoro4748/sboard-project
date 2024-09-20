package com.sboard.controller;

import com.sboard.config.AppInfo;
import com.sboard.dto.TermsDTO;
import com.sboard.dto.UserDTO;
import com.sboard.service.MailSendService;
import com.sboard.service.TermsService;
import com.sboard.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final TermsService termsService;
    private final MailSendService mailSendService;
    private final AppInfo appInfo;

    @Value("${spring.application.version}")
    private String appVersion;

    @GetMapping("/user/login")
    public String login(Model model) {
        model.addAttribute(appInfo);
        return "/user/login";
    }

    @GetMapping("/user/terms")
    public String terms(Model model){
        TermsDTO terms = termsService.selectTerms();
        model.addAttribute("terms",terms);
        return "/user/terms";
    }

    @GetMapping("/user/register")
    public String register(){
        return "/user/register";
    }

    @GetMapping("/user/register/checkUid/{uid}")
    public ResponseEntity<Boolean> checkUid(@PathVariable("uid") String uid){
        log.info("Check uid: " + uid);
        return ResponseEntity.ok(userService.selectUserByUid(uid));
    }
    @GetMapping("/user/register/checkNick/{nick}")
    public ResponseEntity<Boolean> checkNick(@PathVariable("nick") String nick){
        log.info("Check nick: " + nick);
        return ResponseEntity.ok(userService.selectUserByNick(nick));
    }
    @GetMapping("/user/register/checkEmail/{email}")
    public ResponseEntity checkEmail(@PathVariable("email") String email, HttpSession session){
        log.info("Check email " + email);
        if(userService.selectUserByEmail(email)){
            return ResponseEntity.ok(true);
        }
        else {
            String code = mailSendService.joinEmail(email);
            session.setAttribute("code", String.valueOf(code));
            return ResponseEntity.ok(false);
        }
    }

    // 이메일 인증 코드 검사
    @ResponseBody
    @PostMapping("/email")
    public ResponseEntity<?> checkEmail(HttpSession session, @RequestBody Map<String, String> jsonData){

        log.info("checkEmail code : " + jsonData);

        String receiveCode = jsonData.get("code");
        log.info("checkEmail receiveCode : " + receiveCode);

        String sessionCode = (String) session.getAttribute("code");

        if(sessionCode.equals(receiveCode)){
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", true);

            return ResponseEntity.ok().body(resultMap);
        }else{
            // Json 생성
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("result", false);

            return ResponseEntity.ok().body(resultMap);
        }
    }

    @GetMapping("/user/register/checkHp/{hp}")
    public ResponseEntity<Boolean> checkHp(@PathVariable("hp") String hp){
        log.info("Check hp: " + hp);
        return ResponseEntity.ok(userService.selectUserByHp(hp));
    }
    @PostMapping("/user/register")
    public String register(UserDTO userDTO){
        userService.insertUser(userDTO);
        return "redirect:/user/login";
    }
}
