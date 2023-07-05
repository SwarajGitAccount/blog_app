package com.blog.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   System.out.println( passwordEncoder.encode("Swaraj@1234"));
   //System.out.println((passwordEncoder.encode("Vikash@1234")));
    }

}
