package com.blog.controller;

import com.blog.exception.JWTAuthResponse;
import com.blog.payload.SignInDto;
import com.blog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInDto dto){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsernameOrEmail(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // get token form tokenProvider
        String s = tokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JWTAuthResponse(s));
    }

    }


}
