package br.com.apirestfull.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apirestfull.user.model.userModel;
import br.com.apirestfull.user.repository.userRepository;

@Service
public class serviceCreateUser {
    @Autowired
    userRepository repository;
    
    public userModel execute(userModel user) {

        if (user != null) {            
            return this.repository.save(user);
        } else {
            return null;
        }
        
    }
}
