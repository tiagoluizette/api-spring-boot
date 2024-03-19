package br.com.apirestful.user.repository;

import br.com.apirestful.user.model.userModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepository extends JpaRepository<userModel, Integer> {
  public List<userModel> findByName(String name);

  public List<userModel> findByEmail(String email);

  @Query("SELECT u FROM userModel u WHERE u.email = :email")
  public userModel findByUser(@Param("email") String email);
}
