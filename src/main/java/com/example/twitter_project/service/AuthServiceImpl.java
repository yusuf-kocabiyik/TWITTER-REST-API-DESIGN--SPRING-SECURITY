package com.example.twitter_project.service;

import com.example.twitter_project.dto.request.RegisterRequestDto;
import com.example.twitter_project.dto.response.RegisterResponseDto;
import com.example.twitter_project.entity.Role;
import com.example.twitter_project.entity.User;
import com.example.twitter_project.exception.TwitterException;
import com.example.twitter_project.repository.RoleRepository;
import com.example.twitter_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    /// ALL ARGS CONSTRUCTOR İLE DEP INJECTION OTOMATİK YAPILIYOR.
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;



    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        Optional<User> optionalUser = userRepository.findByEmail(registerRequestDto.email());

        if(optionalUser.isPresent()){
            throw new TwitterException("Email is already registered!", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(registerRequestDto.password());

        Role userRole = roleRepository.getByAuthority("USER")
                .orElseThrow(()-> new TwitterException("Role is not found!",HttpStatus.BAD_REQUEST));

        User user = new User();
        user.setEmail(registerRequestDto.email());
        user.setPassword(encodedPassword);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(Set.of(userRole));

        userRepository.save(user);

        return new RegisterResponseDto(user.getEmail(), "Kullanıcı başarıyla kaydolmuştur.");
    }
}
