package br.com.apirestful.server.autenthication;

public class authenticationUndefined implements iSecurityCredential {

  public static authenticationUndefined instance;
  private String _passwordCredential;
  private enumTypeCredential _typeCredential;
  private String _userNameCredential;

  private authenticationUndefined(enumTypeCredential value) {
    this._typeCredential = value;
  }

  public static authenticationUndefined newAuthenticationUndefined() {
    if (instance == null) {
      instance = new authenticationUndefined(enumTypeCredential.UNDEFINED);
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

    return null;
  }
}
