package br.com.apirestful.user.service;

import br.com.apirestful.user.model.userModel;
import br.com.apirestful.user.repository.userRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceListUser {

  @Autowired
  userRepository repository;

  public List<userModel> listAll() {
    return repository.findAll();
  }

  public List<userModel> findByName(String name) {
    return repository.findByName(name);
  }

  public List<userModel> findByEmail(String email) {
    return repository.findByEmail(email);
  }

  public Optional<userModel> findById(Integer id) {
    if (id != null) return repository.findById(id); else return null;
  }
}
