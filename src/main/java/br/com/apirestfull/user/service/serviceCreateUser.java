package br.com.apirestfull.user.service;

import br.com.apirestfull.server.error.serverError;
import br.com.apirestfull.user.model.userModel;
import br.com.apirestfull.user.repository.userRepository;
import br.com.apirestfull.utils.utilsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceCreateUser {

  @Autowired
  userRepository repository;

  public userModel execute(userModel user) {
    if (user != null) {
      userModel verifyUser = repository.findByUser(user.email);

      if (verifyUser != null) {
        throw new serverError("User with email is exists!", 409);
      }

      user.password = utilsServer.passwordEncoder(user.getPassword());

      System.out.println(user);

      return this.repository.save(user);
    }

    return null;
  }
}
