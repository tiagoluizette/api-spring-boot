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
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ListAllUserTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("##### Teste buscar todos usuários | Basic Authentication #####")
  public void listAllUser() {
    String username = "administrator";
    String password = "46a7c06e-4b8e-40b7-8b11-470ef22cf2b1";
    String url = "http://localhost:8080/users/";

    userModel[] users = restTemplate
      .withBasicAuth(username, password)
      .getForObject(url, userModel[].class);

    assertNotNull(users);
    assertEquals(8, users.length);

    ResponseEntity<String> response = restTemplate
      .withBasicAuth(username, password)
      .getForEntity(url, String.class);

    System.out.println("Response status: " + response.getStatusCode().value());
    System.out.println("Response body: " + response.getBody());

    assertEquals(200, response.getStatusCode().value());
  }
}
