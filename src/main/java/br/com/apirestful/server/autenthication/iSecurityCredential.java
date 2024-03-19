package br.com.apirestful.server.autenthication;

public interface iSecurityCredential {
  iSecurityCredential typeCredential(enumTypeCredential value);
  enumTypeCredential typeCredential();
  iSecurityCredential userNameCredential(String value);
  String userNameCredential();
  iSecurityCredential passwordCredential(String value);
  String passwordCredential();

  default iSecurityCredential jwtCredential(String value) {
    return this;
  }

  default String jwtCredential() {
    return "";
  }
}
