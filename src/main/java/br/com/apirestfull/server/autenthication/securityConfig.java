package br.com.apirestfull.server.autenthication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig {

  @Autowired
  private SecurityFilter securityFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    throws Exception {
    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      .authorizeHttpRequests(authoriza ->
        authoriza
          .requestMatchers(HttpMethod.POST, "/users/create_token")
          .permitAll()
          .requestMatchers(HttpMethod.GET, "/users/")
          .permitAll()
          .requestMatchers("/users/usersEmail/*")
          .permitAll()
          .requestMatchers("/users/usersName/*")
          .permitAll()
          .requestMatchers("/api/status")
          .permitAll()
          .anyRequest()
          .authenticated()
      )
      .addFilterBefore(
        securityFilter,
        UsernamePasswordAuthenticationFilter.class
      )
      .build();
  }
}
