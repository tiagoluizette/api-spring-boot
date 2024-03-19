package br.com.apirestful.server.autenthication;

public class authenticationJWT implements iSecurityCredential {

  public static authenticationJWT instance;
  private String _passwordCredential;
  private enumTypeCredential _typeCredential;
  private String _userNameCredential;
  private String _jwtCredential;

  private authenticationJWT(enumTypeCredential value) {
    this._typeCredential = value;
  }

  public static authenticationJWT newAuthenticationJWT() {
    if (instance == null) {
      instance = new authenticationJWT(enumTypeCredential.JWT);
    }

    return instance;
  }

  @Override
  public String passwordCredential() {
    return this._passwordCredential;
  }

  @Override
  public iSecurityCredential passwordCredential(String value) {
    this._passwordCredential = value;

    return null;
  }

  @Override
  public enumTypeCredential typeCredential() {
    return this._typeCredential;
  }

  @Override
  public iSecurityCredential typeCredential(enumTypeCredential value) {
    this._typeCredential = value;
    return null;
  }

  @Override
  public String userNameCredential() {
    return this._userNameCredential;
  }

  @Override
  public iSecurityCredential userNameCredential(String value) {
    this._userNameCredential = value;

    return this;
  }

  @Override
  public String jwtCredential() {
    return this._jwtCredential;
  }

  @Override
  public iSecurityCredential jwtCredential(String value) {
    this._jwtCredential = value;

    return this;
  }
}
