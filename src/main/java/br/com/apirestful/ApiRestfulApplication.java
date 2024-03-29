package br.com.apirestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ApiRestfulApplication {

  public static ApplicationContext context;

  public static void main(String[] args) {
    context = SpringApplication.run(ApiRestfulApplication.class, args);
  }
}
