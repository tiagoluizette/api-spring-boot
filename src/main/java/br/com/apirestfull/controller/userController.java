package br.com.apirestfull.controller;
import org.springframework.web.bind.annotation.RestController;
import br.com.apirestfull.model.userModel;
import br.com.apirestfull.repository.userRepository;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class userController {    

    private List<userModel> listUsers = new ArrayList<>();

    @Autowired
    public userRepository repository; 

    @GetMapping("/{id}")
    public userModel user(@PathVariable("id") Integer id) {
        Optional<userModel> userFind = this.listUsers.stream().filter(user -> user.id == id).findFirst();

        if (userFind.isPresent()) {
            return userFind.get();
        }

        return null;
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        if (id != null) {
            
            this.repository.deleteById(id);
        }


        System.out.println(id);
    }
    
    @GetMapping("/")
    public List<userModel> users() {
        return this.repository.findAll();
    }

    @GetMapping("/usersEmail/{email}")
    public List<userModel> usersEmail(@PathVariable("email") String email) {

        List<userModel> listaUsersAux = this.repository.findByEmail(email);

        if (listaUsersAux != null) {
            return listaUsersAux;
        }

        return null;

    }
    
    @GetMapping("/usersName/{name}")
    public List<userModel> usersName(@PathVariable("name") String name) {

        List<userModel> listaUsersAux = this.repository.findByName(name);

        if (listaUsersAux != null) {
            return listaUsersAux;
        }
        
        return null; 

    }
    
    @PutMapping("/")
    public userModel updateUser(@RequestBody userModel user) {
        if (user != null) {
            return this.repository.save(user);
        }

        return null;
    }

    @PostMapping("/")
    public userModel registerUser(@RequestBody userModel userTemp) {
        listUsers.add(userTemp);

        if (userTemp != null) {
            return this.repository.save(userTemp);            
        } else {
            return null;
        }        
    }
}
