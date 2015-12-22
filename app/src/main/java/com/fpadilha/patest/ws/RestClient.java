package com.fpadilha.patest.ws;

import android.content.Context;

import com.fpadilha.patest.models.request.CreateSessionRequest;
import com.fpadilha.patest.models.request.SignInRequest;
import com.fpadilha.patest.models.request.SignUpRequest;
import com.fpadilha.patest.models.request.UpdateUserRequest;
import com.fpadilha.patest.models.response.CreateSessionResponse;
import com.fpadilha.patest.models.response.SignInResponse;
import com.fpadilha.patest.models.response.SignUpResponse;
import com.fpadilha.patest.models.response.UpdateUserResponse;

import java.io.File;
import java.io.InputStream;

/**
 * Created by felipe on 12/12/2015.
 */
public interface RestClient {

    CreateSessionResponse createSession(CreateSessionRequest request);

    SignUpResponse signUp(SignUpRequest request);

    SignInResponse signIn(SignInRequest request);

    UpdateUserResponse updateUser(UpdateUserRequest request);

    InputStream downloadFile(Integer userProfilePictureID);

    InputStream uploadFile(File imageFile);

}
