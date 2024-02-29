package br.com.apirestfull.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirestfull.user.repository.userRepository;

@Service
public class serviceDeleteUser {
    @Autowired
    userRepository repository;
    
    public void execute(Integer id) {
        if (id != null)
        this.repository.deleteById(id);            
        
    }
}
