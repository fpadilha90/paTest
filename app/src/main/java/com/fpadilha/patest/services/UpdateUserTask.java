package com.fpadilha.patest.services;

import android.os.AsyncTask;

import com.fpadilha.patest.models.User;
import com.fpadilha.patest.models.request.UpdateUserRequest;
import com.fpadilha.patest.models.response.UpdateUserResponse;
import com.fpadilha.patest.ws.HTTPRestClient;
import com.fpadilha.patest.ws.RestClient;

/**
 * Created by felipe on 21/12/2015.
 */
public class UpdateUserTask {

    private TaskCallback callback;
    private RestClient restClient;
    private User user;

    public UpdateUserTask() {
        restClient = new HTTPRestClient();
    }


    public void start(TaskCallback callback, User user) {
        this.user = user;
        this.callback = callback;

        doInBackground();
    }

    private void doInBackground() {
        new AsyncTask<Void, Void, UpdateUserResponse>() {
            @Override
            protected UpdateUserResponse doInBackground(Void... params) {
                UpdateUserRequest request = new UpdateUserRequest();

                User userRequest = new User();
                userRequest.setLogin(user.getLogin());
                userRequest.setBlobId(user.getBlobId());
                userRequest.setEmail(user.getEmail());
                userRequest.setExternalUserId(user.getExternalUserId());
                userRequest.setFacebookId(user.getFacebookId());
                userRequest.setTwitterId(user.getTwitterId());
                userRequest.setFullName(user.getFullName());
                userRequest.setPhone(user.getPhone());
                userRequest.setWebsite(user.getWebsite());
                userRequest.setTagList(user.getTagList());
                userRequest.setCustomData(user.getCustomData());
                userRequest.setPassword(user.getPassword());
                userRequest.setOldPassword(user.getOldPassword());

                request.setUser(userRequest);

                return restClient.updateUser(request);
            }

            @Override protected void onPostExecute(UpdateUserResponse response) {
                callback.onSuccess(response);

            }
        }.execute();
    }

}
