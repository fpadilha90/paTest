package com.fpadilha.patest.models.response;

import com.fpadilha.patest.models.User;

/**
 * Created by fpadilha on 15/12/2015.
 */
public class SignUpResponse extends BaseResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
