package br.com.apirestfull.server.controller;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class serverController {

  @GetMapping("/status")
  public Object getApiStatus() {
    var response = new HashMap<String, Object>();

    response.put("service", "API-Spring-Boot");
    response.put("statup", "Up");
    response.put("httpStatus", HttpStatus.OK);

    return response;
  }
}
