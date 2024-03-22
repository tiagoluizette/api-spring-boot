package br.com.apirestful.server.autenthication;

import br.com.apirestful.server.autenthication.strategy.authenticationBasic;
import br.com.apirestful.server.autenthication.strategy.authenticationJWT;
import br.com.apirestful.server.autenthication.strategy.authenticationUndefined;
import br.com.apirestful.server.utils.UtilsAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class stragetyAuthentication {

  static stragetyAuthentication instance;

  @Autowired
  authenticationBasic basicAuthentication;

  @Autowired
  authenticationUndefined authenticationUndefined;

  @Autowired
  authenticationJWT jwtAuthentication;

  private String BASIC = "Basic ";

  private String BEARER = "Bearer ";

  public static stragetyAuthentication newStragetyAuthentication() {
    if (instance == null) {
      instance = new stragetyAuthentication();
    }

    return instance;
  }

  Boolean authentication(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws IOException {
    String authHeaderJWT = request.getHeader("Authorization");

    if (authHeaderJWT != null) {
      if (!authHeaderJWT.split(" ")[0].equals(BEARER)) {
        String authHeaderBasic = request.getHeader("Authorization");

        Boolean authBasic = authHeaderBasic.startsWith(BASIC);

        if (authBasic) {
          String[] credentials = UtilsAuthentication
            .decodeBase64(request.getHeader("Authorization").replace(BASIC, ""))
            .split(":");

          String username = credentials[0];
          String password = credentials[1];

          basicAuthentication = new authenticationBasic();

          return basicAuthentication
            .userNameCredential(username)
            .passwordCredential(password)
            .response(response)
            .execute();
        } else {
          String token = authHeaderJWT.split(" ")[1];

          jwtAuthentication = new authenticationJWT();

          return jwtAuthentication
            .jwtCredential(token)
            .response(response)
            .execute();
        }
      }
    } else {
      authenticationUndefined = new authenticationUndefined();

      return authenticationUndefined.response(response).execute();
    }

    return false;
  }
}
