package com.fpadilha.patest.ws;

import com.fpadilha.patest.models.request.CreateSessionRequest;
import com.fpadilha.patest.models.response.CreateSessionResponse;

/**
 * Created by felipe on 12/12/2015.
 */
public interface RestClient {

    CreateSessionResponse createSession(CreateSessionRequest request);

}
