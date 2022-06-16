package com.ssw.nosleep4.service;


import com.ssw.nosleep4.domain.User;
import com.ssw.nosleep4.dto.LoginRequetsDto;
import com.ssw.nosleep4.dto.UserRequestsDto;
import com.ssw.nosleep4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void createUser(UserRequestsDto requestsDto) {
        String nickname = requestsDto.getNickname();
        String username = requestsDto.getUsername();
        String password = requestsDto.getPassword();
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("nickname = " + nickname);

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        Optional<User> foundid = userRepository.findByNickname(nickname);
        if (foundid.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 닉네임이 존재합니다.");
        }
        if (password.length() < 6){
            throw new IllegalArgumentException("비밀번호는 6자 이상 입력해주세요");
        }
            //패스워드 암호화
            password = passwordEncoder.encode(requestsDto.getPassword());


            User user = new User(nickname, username, password);
            System.out.println("user = " + user);
            userRepository.save(user);

    }
    public Boolean login(LoginRequetsDto loginRequetsDto){
        User user = userRepository.findByUsername(loginRequetsDto.getUsername())
                .orElse(null);
        if (user != null) {
            if (!passwordEncoder.matches(loginRequetsDto.getPassword(), user.getPassword())) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
