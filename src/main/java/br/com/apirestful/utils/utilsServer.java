package br.com.apirestful.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class utilsServer {

  public static String passwordEncoder(String value) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    return passwordEncoder.encode(value);
  }

  public static boolean checkPassword(
    String userPassword,
    String loginPassword
  ) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    return passwordEncoder.matches(loginPassword, userPassword);
  }
}
