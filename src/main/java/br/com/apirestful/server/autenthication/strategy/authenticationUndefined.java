package br.com.apirestful.server.autenthication.strategy;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class authenticationUndefined implements iStrategyCredential {

  private HttpServletResponse _response;

  @Override
  public iStrategyCredential response(HttpServletResponse value) {
    this._response = value;

    return this;
  }

  @Override
  public Boolean execute() throws IOException {
    // Defina o status da resposta
    /*this._response.setStatus(HttpStatus.CONFLICT.value());
    this._response.getWriter().write("Authentication not informed!");
    return false;
    */

    this._response.setStatus(HttpStatus.OK.value());
    System.out.println("Authentication not informed!");

    return true;
  }
}
