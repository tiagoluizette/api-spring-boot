package br.com.apirestfull.server.service;

import br.com.apirestfull.user.model.userModel;
import br.com.apirestfull.user.repository.userRepository;
import br.com.apirestfull.utils.utilsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceCheckAuthenticationUser {

  public String userName;
  public String password;

  @Autowired
  userRepository repository;

  public serviceCheckAuthenticationUser userName(String value) {
    this.userName = value;

    return this;
  }

  public serviceCheckAuthenticationUser password(String value) {
    this.password = value;

    return this;
  }

  public userModel validateUserAuthenticate() {
    userModel user = repository.findByUser(this.userName);

    String passwordUser = user.getPassword();

    if (utilsServer.checkPassword(passwordUser, this.password)) return user;

    return null;
  }
}
