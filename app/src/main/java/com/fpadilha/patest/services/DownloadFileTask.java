package com.fpadilha.patest.services;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.fpadilha.patest.ws.HTTPRestClient;
import com.fpadilha.patest.ws.RestClient;

import java.io.InputStream;

/**
 * Created by felipe on 22/12/2015.
 */
public class DownloadFileTask {

    private DownloadFileCallback callback;
    private RestClient restClient;
    private Integer userProfilePictureID;

    public DownloadFileTask() {
        restClient = new HTTPRestClient();
    }

    public void start(DownloadFileCallback callback, Integer userProfilePictureID) {
        this.callback = callback;
        this.userProfilePictureID = userProfilePictureID;

        doInBackground();
    }

    private void doInBackground() {
        new AsyncTask<Void, Void, InputStream>() {
            @Override
            protected InputStream doInBackground(Void... params) {

                return restClient.downloadFile(userProfilePictureID);
            }

            @Override protected void onPostExecute(InputStream inputStream) {
                callback.onFinish(inputStream);

            }
        }.execute();
    }
}
