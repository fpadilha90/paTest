package com.fpadilha.patest.services;

import android.os.AsyncTask;

import com.fpadilha.patest.models.User;
import com.fpadilha.patest.models.request.SignInRequest;
import com.fpadilha.patest.models.request.SignUpRequest;
import com.fpadilha.patest.models.response.SignInResponse;
import com.fpadilha.patest.models.response.SignUpResponse;
import com.fpadilha.patest.ws.HTTPRestClient;
import com.fpadilha.patest.ws.RestClient;

/**
 * Created by fpadilha on 15/12/2015.
 */
public class SignInTask {

    private TaskCallback callback;
    private RestClient restClient;
    private User user;

    public SignInTask() {
        restClient = new HTTPRestClient();
    }


    public void start(TaskCallback callback, User user) {
        this.user = user;
        this.callback = callback;

        doInBackground();
    }


    private void doInBackground() {
        new AsyncTask<Void, Void, SignInResponse>() {
            @Override
            protected SignInResponse doInBackground(Void... params) {
                SignInRequest request = new SignInRequest();
                request.setLogin(user.getLogin());
                request.setPassword(user.getPassword());

                return restClient.signIn(request);
            }

            @Override protected void onPostExecute(SignInResponse response) {
                callback.onSuccess(response);

            }
        }.execute();
    }
}
