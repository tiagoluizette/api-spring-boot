package br.com.apirestful.server.autenthication.strategy;

import br.com.apirestful.server.autenthication.enumTypeCredential;
import br.com.apirestful.server.service.serviceCheckAuthenticationUser;
import br.com.apirestful.user.model.userModel;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class authenticationBasic implements iStrategyCredential {

  public static authenticationBasic instance;
  private String _passwordCredential;
  private enumTypeCredential _typeCredential;
  private String _userNameCredential;
  private HttpServletResponse _response;

  @Autowired
  private serviceCheckAuthenticationUser serviceAuthentication;

  public authenticationBasic() {
    this._typeCredential = enumTypeCredential.BASIC;
  }

  @Override
  public iStrategyCredential response(HttpServletResponse value) {
    this._response = value;

    return this;
  }

  @Override
  public Boolean execute() throws IOException {
    serviceAuthentication = new serviceCheckAuthenticationUser();

    userModel user = serviceAuthentication
      .userName(this._userNameCredential)
      .password(this._passwordCredential)
      .validateUserAuthenticate();

    if (user == null) {
      this._response.setStatus(HttpStatus.CONFLICT.value());
      this._response.getWriter().write("Credentials user invalid!");

      return false;
    } else {
      return true;
    }
  }

  public String passwordCredential() {
    return this._passwordCredential;
  }

  public authenticationBasic passwordCredential(String value) {
    this._passwordCredential = value;

    return this;
  }

  public enumTypeCredential typeCredential() {
    return this._typeCredential;
  }

  public String userNameCredential() {
    return this._userNameCredential;
  }

  public authenticationBasic userNameCredential(String value) {
    this._userNameCredential = value;

    return this;
  }
}
