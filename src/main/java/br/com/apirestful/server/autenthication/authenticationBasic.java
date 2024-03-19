package br.com.apirestful.server.autenthication;

public class authenticationBasic implements iSecurityCredential {

  public static authenticationBasic instance;
  private String _passwordCredential;
  private enumTypeCredential _typeCredential;
  private String _userNameCredential;

  private authenticationBasic(enumTypeCredential value) {
    this._typeCredential = value;
  }

  public static authenticationBasic newAuthenticationBasic() {
    if (instance == null) {
      instance = new authenticationBasic(enumTypeCredential.BASIC);
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

    return this;
  }

  @Override
  public enumTypeCredential typeCredential() {
    return this._typeCredential;
  }

  @Override
  public iSecurityCredential typeCredential(enumTypeCredential value) {
    this._typeCredential = value;
    return this;
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
}
