package br.com.apirestful;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.apirestful.user.model.userModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ListUserEmailTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("##### Listar usuário através do email #####")
  public void listUserEmail() {
    userModel[] users = restTemplate.getForObject(
      "http://localhost:8080/users/usersEmail/email@gmail.com.br.br",
      userModel[].class
    );

    assertNotNull(users);
    assertEquals(0, users);
  }
}
