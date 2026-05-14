package com.example.storeservice.service;

import com.example.storeservice.dto.LoginResponse;
import com.example.storeservice.dto.RegisterRequest;
import com.example.storeservice.entity.User;
import com.example.storeservice.repository.UserRepository;
import com.example.storeservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String[] ADJECTIVES = {"快乐的", "聪明的", "勇敢的", "可爱的", "善良的", "活泼的", "温柔的", "阳光的"};
    private static final String[] NOUNS = {"小猫", "小狗", "小兔", "小熊", "小鹿", "小鸟", "小鱼", "小松鼠"};

    public LoginResponse login(String phone, String password) {
        User user = userRepository.findByPhone(phone).orElse(null);
        
        if (user == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("密码错误");
        }
        
        String token = jwtUtil.generateToken(user.getId());
        return new LoginResponse(token, user.getId(), user.getNickname(), user.getPhone());
    }

    public LoginResponse register(RegisterRequest request) {
        if (userRepository.findByPhone(request.getPhone()).isPresent()) {
            throw new IllegalArgumentException("该手机号已被注册");
        }
        
        String nickname = request.getNickname();
        if (nickname == null || nickname.trim().isEmpty()) {
            nickname = generateRandomNickname();
        }
        
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getPhone(), encodedPassword, nickname, null);
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getId());
        return new LoginResponse(token, user.getId(), user.getNickname(), user.getPhone());
    }

    private String generateRandomNickname() {
        Random random = new Random();
        String adjective = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[random.nextInt(NOUNS.length)];
        int number = random.nextInt(1000);
        return adjective + noun + number;
    }
}