package br.com.apirestfull.server.service;

import br.com.apirestfull.server.dtos.AuthDTO;

public interface IAuthenticationService {
    public String createToken(AuthDTO authDTO);
    public String validateToken(String token);
}
