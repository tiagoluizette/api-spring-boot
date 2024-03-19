package br.com.apirestful.server.service;

import br.com.apirestful.server.dtos.AuthDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Override
  public String createToken(AuthDTO authDTO) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

      return JWT
        .create()
        .withIssuer("api-restfull")
        .withSubject(authDTO.getEmail())
        .withExpiresAt(this.geraDataExpiracao(1))
        .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new RuntimeException(
        "Erro ao tentar gerar o token! " + exception.getMessage()
      );
    }
  }

  @Override
  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

      return JWT
        .require(algorithm)
        .withIssuer("api-restfull")
        .build()
        .verify(token)
        .getSubject();
    } catch (JWTVerificationException exception) {
      return "";
    }
  }

  private Instant geraDataExpiracao(Integer duration) {
    return LocalDateTime
      .now()
      .plusHours(duration)
      .toInstant(ZoneOffset.of("-03:00"));
  }
}
