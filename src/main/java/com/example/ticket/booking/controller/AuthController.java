package com.example.ticket.booking.controller;

import com.example.ticket.booking.dto.JwtAuthResponse;
import com.example.ticket.booking.dto.LoginDto;
import com.example.ticket.booking.dto.RegisterDto;
import com.example.ticket.booking.model.Role;
import com.example.ticket.booking.model.User;
import com.example.ticket.booking.repository.RoleRepository;
import com.example.ticket.booking.repository.UserRepository;
import com.example.ticket.booking.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.security.auth.login.LoginException;
import java.util.Collections;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController

@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins="http://localhost:4200")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> userLogin(@RequestBody LoginDto loginDto) {
        try{
            System.out.println(loginDto.toString());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token=jwtTokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtAuthResponse(token,"bearer",userRepository.findByEmail(authentication.getName()).orElseThrow()));
        }
        catch (AuthenticationException e){
            System.out.println(e.getMessage());
            return new ResponseEntity<JwtAuthResponse>(new JwtAuthResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> registerUser(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>(new JwtAuthResponse("email is already taken!"), HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB

        // create user object
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>(new JwtAuthResponse("helloword"), HttpStatus.OK);



    }
//
//@Bean
//SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//    http
//            // ...
//            .cors(cors -> cors.disable());
//    return http.build();
//
//}
//
    }
