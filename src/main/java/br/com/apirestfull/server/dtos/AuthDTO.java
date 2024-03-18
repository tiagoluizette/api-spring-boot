package br.com.apirestfull.server.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    public String userName;
    public String password;
    public String email;
}
