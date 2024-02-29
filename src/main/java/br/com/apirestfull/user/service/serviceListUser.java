package br.com.apirestfull.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirestfull.user.model.userModel;
import br.com.apirestfull.user.repository.userRepository;

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
        if (id != null)
        return this.repository.findById(id);
       else
        return null; 
    }
        
}
