package com.fpadilha.patest.models.response;

import com.fpadilha.patest.models.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fpadilha on 15/12/2015.
 */
public class SignInResponse extends BaseResponse {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
