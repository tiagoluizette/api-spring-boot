package br.com.apirestfull.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.apirestfull.user.model.userModel;

public interface userRepository extends JpaRepository<userModel, Integer> {
        
    public List<userModel> findByName(String name);       

    public List<userModel> findByEmail(String email);
    
    @Query("SELECT u FROM userModel u WHERE u.email = :email")
    public userModel findByUser(@Param("email") String email);
    
}
