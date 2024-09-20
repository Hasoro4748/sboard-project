package com.sboard.service;

import com.sboard.dto.UserDTO;
import com.sboard.entity.User;
import com.sboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void insertUser(UserDTO userDTO) {
        String encoded = passwordEncoder.encode(userDTO.getPass());
        userDTO.setPass(encoded);

        User user = userDTO.toEntity();
        userRepository.save(user);
    }
    public boolean selectUserByUid(String uid) {
        return userRepository.existsByUid(uid);
    }
    public boolean selectUserByUsername(String username) {
        return userRepository.existsByName(username);
    }
    public boolean selectUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }public boolean selectUserByNick(String Nick) {
        return userRepository.existsByNick(Nick);
    }
    public boolean selectUserByHp(String Hp) {
        return userRepository.existsByHp(Hp);
    }

}
