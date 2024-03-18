package br.com.apirestfull.server.autenthication;

import br.com.apirestfull.server.utils.UtilsAuthentication;
import jakarta.servlet.http.HttpServletRequest;

public class facadeAuthentication {

  static facadeAuthentication instance;

  private String BASIC = "Basic ";
  private String BEARER = "Bearer ";

  public static facadeAuthentication newFacadeAuthentication() {
    if (instance == null) {
      instance = new facadeAuthentication();
    }

    return instance;
  }

  iSecurityCredential authenticationHeader(HttpServletRequest request) {
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

          return authenticationBasic
            .newAuthenticationBasic()
            .userNameCredential(username)
            .passwordCredential(password);
        } else {
          String token = authHeaderJWT.split(" ")[1];

          return authenticationJWT.newAuthenticationJWT().jwtCredential(token);
        }
      }
    }

    return authenticationUndefined.newAuthenticationUndefined();
  }
}
