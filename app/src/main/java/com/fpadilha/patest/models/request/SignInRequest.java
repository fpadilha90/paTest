package com.fpadilha.patest.models.request;

/**
 * Created by fpadilha on 15/12/2015.
 */
public class SignInRequest {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
