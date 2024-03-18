package br.com.apirestfull.user.controller;

import br.com.apirestfull.server.autenthication.securityConfig;
import br.com.apirestfull.server.dtos.AuthDTO;
import br.com.apirestfull.server.service.IAuthenticationService;
import br.com.apirestfull.user.model.userModel;
import br.com.apirestfull.user.service.serviceCreateUser;
import br.com.apirestfull.user.service.serviceDeleteUser;
import br.com.apirestfull.user.service.serviceListUser;
import br.com.apirestfull.user.service.serviceUpdateUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class userController {

  private List<userModel> listUsers = new ArrayList<>();

  @Autowired
  serviceListUser listService;

  @Autowired
  serviceCreateUser createService;

  @Autowired
  serviceDeleteUser deleteService;

  @Autowired
  serviceUpdateUser updateService;

  @Autowired
  securityConfig config;

  @Autowired
  IAuthenticationService authenticationService;

  @PostMapping("/create_token")
  public String createToken(@RequestBody userModel userModel) {
    AuthDTO authDTO = new AuthDTO();

    authDTO.password = userModel.password;
    authDTO.userName = userModel.name;
    authDTO.email = userModel.email;

    return authenticationService.createToken(authDTO);
  }

  @PostMapping("/")
  public userModel registerUser(@RequestBody userModel userTemp) {
    listUsers.add(userTemp);

    System.out.println(userTemp);

    if (userTemp != null) {
      return createService.execute(userTemp);
    } else {
      return null;
    }
  }

  @PutMapping("/")
  public userModel updateUser(@RequestBody userModel user) {
    if (user != null) {
      return updateService.execute(user);
    }

    return null;
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Integer id) {
    if (id != null) {
      deleteService.execute(id);
    }

    System.out.println(id);
  }

  @GetMapping("/")
  public List<userModel> users() {
    return listService.listAll();
  }

  @GetMapping("/memory/{id}")
  public userModel userMemory(@PathVariable("id") Integer id) {
    Optional<userModel> userFind =
      this.listUsers.stream().filter(user -> user.id == id).findFirst();

    if (userFind.isPresent()) {
      return userFind.get();
    }

    return null;
  }

  @GetMapping("/{id}")
  public Optional<userModel> userID(@PathVariable("id") Integer id) {
    return this.listService.findById(id);
  }

  @GetMapping("/usersEmail/{email}")
  public List<userModel> usersEmail(@PathVariable("email") String email) {
    List<userModel> listaUsersAux = listService.findByEmail(email);

    if (listaUsersAux != null) {
      return listaUsersAux;
    }

    return null;
  }

  @GetMapping("/usersName/{name}")
  public List<userModel> usersName(@PathVariable("name") String name) {
    List<userModel> listaUsersAux = listService.findByName(name);

    if (listaUsersAux != null) {
      return listaUsersAux;
    }

    return null;
  }
}
