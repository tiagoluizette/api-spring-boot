package br.com.apirestful.user.service;

import br.com.apirestful.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceDeleteUser {

  @Autowired
  userRepository repository;

  public void execute(Integer id) {
    if (id != null) this.repository.deleteById(id);
  }
}
