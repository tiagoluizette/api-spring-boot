package br.com.apirestful.server.autenthication.strategy;

import br.com.apirestful.server.autenthication.enumTypeCredential;
import br.com.apirestful.server.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class authenticationJWT implements iStrategyCredential {

  public static authenticationJWT instance;
  private enumTypeCredential _typeCredential;
  private String _jwtCredential;
  public HttpServletResponse _response;

  @Autowired
  private AuthenticationService authenticationService;

  public authenticationJWT() {
    this._typeCredential = enumTypeCredential.JWT;
  }

  @Override
  public iStrategyCredential response(HttpServletResponse value) {
    this._response = value;

    return this;
  }

  @Override
  public Boolean execute() throws IOException {
    authenticationService = new AuthenticationService();

    String tokenValid = authenticationService.validateToken(
      this._jwtCredential
    );

    if (tokenValid == "") {
      // Defina o status da resposta
      this._response.setStatus(HttpStatus.UNAUTHORIZED.value());
      this._response.getWriter().write("Invalid token!");

      return false;
    } else {
      return true;
    }
  }

  public enumTypeCredential typeCredential() {
    return this._typeCredential;
  }

  public authenticationJWT typeCredential(enumTypeCredential value) {
    this._typeCredential = value;
    return null;
  }

  public String jwtCredential() {
    return this._jwtCredential;
  }

  public authenticationJWT jwtCredential(String value) {
    this._jwtCredential = value;

    return this;
  }
}
