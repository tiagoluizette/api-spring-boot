package br.com.apirestful.server.autenthication.strategy;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public interface iStrategyCredential {
  iStrategyCredential response(HttpServletResponse value);
  Boolean execute() throws IOException;
}
