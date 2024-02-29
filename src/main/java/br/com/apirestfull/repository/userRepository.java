package br.com.apirestfull.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apirestfull.model.userModel;

public interface userRepository extends JpaRepository<userModel, Integer> {
        
    public List<userModel> findByName(String name);       
    public List<userModel> findByEmail(String email);       
    
}
