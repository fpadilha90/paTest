package com.fpadilha.patest.services;

import android.os.AsyncTask;

import com.fpadilha.patest.ws.HTTPRestClient;
import com.fpadilha.patest.ws.RestClient;

import java.io.File;
import java.io.InputStream;

/**
 * Created by felipe on 22/12/2015.
 */
public class UploadFileTask {

    private RestClient restClient;
    private File imageFile;


    public UploadFileTask() {
        restClient = new HTTPRestClient();
    }

    public void start(File imageFile) {
        this.imageFile = imageFile;


        doInBackground();
    }

    private void doInBackground() {

        new AsyncTask<Void, Void, InputStream>() {
            @Override
            protected InputStream doInBackground(Void... params) {

                return restClient.uploadFile(imageFile);
            }

            @Override protected void onPostExecute(InputStream inputStream) {
//                callback.onFinish(inputStream);

            }
        }.execute();
    }
}
