package br.com.apirestful.server.autenthication;

import br.com.apirestful.server.service.AuthenticationService;
import br.com.apirestful.server.service.serviceCheckAuthenticationUser;
import br.com.apirestful.user.model.userModel;
import br.com.apirestful.user.repository.userRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// middleware de authentication basic ou jwt...
@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  serviceCheckAuthenticationUser serviceCheckAuthenticationUser;

  @Autowired
  AuthenticationService authenticationService;

  @Autowired
  userRepository repository;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    iSecurityCredential dataAuthentication = facadeAuthentication
      .newFacadeAuthentication()
      .authenticationHeader(request);

    String method = request.getMethod();
    String uri = request.getRequestURI();

    if (method.equals("POST") && uri.equals("/users/")) {} else {
      if (dataAuthentication != null) {
        if (
          dataAuthentication.typeCredential() == enumTypeCredential.UNDEFINED
        ) {
          // Defina o status da resposta
          /*response.setStatus(HttpStatus.CONFLICT.value());          
          response.getWriter().write("Authentication not informed!");
          return;*/

          System.out.println("Authentication not informed!");
        } else {
          if (dataAuthentication.typeCredential() == enumTypeCredential.BASIC) {
            userModel user = serviceCheckAuthenticationUser
              .userName(dataAuthentication.userNameCredential())
              .password(dataAuthentication.passwordCredential())
              .validateUserAuthenticate();

            if (user == null) {
              response.setStatus(HttpStatus.CONFLICT.value());
              response.getWriter().write("Credentials user invalid!");

              return;
            }
          } else {
            if (dataAuthentication.typeCredential() == enumTypeCredential.JWT) {
              String tokenValid = authenticationService.validateToken(
                dataAuthentication.jwtCredential()
              );

              if (tokenValid == "") {
                // Defina o status da resposta
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("Invalid token!");

                return;
              }
            }
          }
        }
      }
    }

    // passa para proximo método next()...
    filterChain.doFilter(request, response);
  }
}
