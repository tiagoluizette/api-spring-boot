package br.com.apirestful.server.autenthication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// middleware de authentication basic ou jwt...
@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    String method = request.getMethod();
    String uri = request.getRequestURI();

    if (method.equals("POST") && uri.equals("/users/")) {
      // passa para proximo método next()...
      filterChain.doFilter(request, response);
    } else {
      Boolean resultAuthentication = stragetyAuthentication
        .newStragetyAuthentication()
        .authentication(request, response);

      // passa para proximo método next()...
      if (resultAuthentication) {
        filterChain.doFilter(request, response);
      }
    }
  }
}
