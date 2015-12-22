package com.fpadilha.patest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.fpadilha.patest.R;
import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.models.Session;
import com.fpadilha.patest.models.response.BaseResponse;
import com.fpadilha.patest.models.response.CreateSessionResponse;
import com.fpadilha.patest.services.CreateSessionTask;
import com.fpadilha.patest.services.TaskCallback;

public class SplashActivity extends Activity implements TaskCallback {

    private ProgressBar progressBar;
    private CreateSessionTask createSessionTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflateViews();
        afterView();
    }

    private void inflateViews() {
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void afterView() {
        createSession();
    }

    private void createSession() {
        // Create QuickBlox session
        createSessionTask = new CreateSessionTask();
        createSessionTask.start(this);
    }

    private void startSignIn() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onSuccess(BaseResponse response) {
        if (response instanceof CreateSessionResponse) {
            Session session = ((CreateSessionResponse) response).getSession();
            DataHolder.getDataHolder().setToken(session.getToken());

            startSignIn();
        }
    }

    @Override
    public void onFailed(String error) {

    }
}