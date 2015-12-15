package com.fpadilha.patest.models.response;

import com.fpadilha.patest.models.Session;

/**
 * Created by felipe on 12/12/2015.
 */
public class CreateSessionResponse extends BaseResponse{

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
