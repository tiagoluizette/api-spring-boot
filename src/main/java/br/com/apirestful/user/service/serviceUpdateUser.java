package br.com.apirestful.user.service;

import br.com.apirestful.user.model.userModel;
import br.com.apirestful.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceUpdateUser {

  @Autowired
  userRepository repository;

  public userModel execute(userModel user) {
    if (user != null) return this.repository.save(user); else return null;
  }
}
