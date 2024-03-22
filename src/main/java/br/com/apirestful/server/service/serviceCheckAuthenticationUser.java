package br.com.apirestful.server.service;

import br.com.apirestful.*;
import br.com.apirestful.user.model.userModel;
import br.com.apirestful.user.repository.userRepository;
import br.com.apirestful.utils.utilsServer;
import org.springframework.stereotype.Service;

@Service
public class serviceCheckAuthenticationUser {

  public String userName;
  public String password;

  userRepository repository;

  public serviceCheckAuthenticationUser() {}

  public serviceCheckAuthenticationUser userName(String value) {
    this.userName = value;

    return this;
  }

  public serviceCheckAuthenticationUser password(String value) {
    this.password = value;

    return this;
  }

  public userModel validateUserAuthenticate() {
    repository = ApiRestfulApplication.context.getBean(userRepository.class);

    userModel user = repository.findByUser(userName);

    String passwordUser = user.getPassword();

    if (utilsServer.checkPassword(passwordUser, password)) return user;

    return null;
  }
}
