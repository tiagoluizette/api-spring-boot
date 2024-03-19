package br.com.apirestful.server.service;

import br.com.apirestful.server.dtos.AuthDTO;

public interface IAuthenticationService {
  public String createToken(AuthDTO authDTO);

  public String validateToken(String token);
}
