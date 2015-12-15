package com.fpadilha.patest.services;

import android.os.AsyncTask;

import com.fpadilha.patest.models.User;
import com.fpadilha.patest.models.request.SignUpRequest;
import com.fpadilha.patest.models.response.SignUpResponse;
import com.fpadilha.patest.ws.HTTPRestClient;
import com.fpadilha.patest.ws.RestClient;

/**
 * Created by fpadilha on 15/12/2015.
 */
public class SignUpTask {

    private TaskCallback callback;
    private RestClient restClient;
    private User user;

    public SignUpTask() {
        restClient = new HTTPRestClient();
    }


    public void start(TaskCallback callback, User user) {
        this.user = user;
        this.callback = callback;

        doInBackground();
    }


    private void doInBackground() {
        new AsyncTask<Void, Void, SignUpResponse>() {
            @Override
            protected SignUpResponse doInBackground(Void... params) {
                SignUpRequest request = new SignUpRequest();

                request.setUser(user);

                return restClient.signUp(request);
            }

            @Override protected void onPostExecute(SignUpResponse response) {
                callback.onSuccess(response);
            }
        }.execute();
    }
}
