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

    private Context context;
    private ProgressBar progressBar;
    private CreateSessionTask createSessionTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

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
        // Initialize QuickBlox application with credentials.
//        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);

//        QBAuth.createSession(new QBEntityCallbackImpl<QBSession>() {
//            @Override
//            public void onSuccess(QBSession qbSession, Bundle bundle) {
////                DataHolder.getDataHolder().setSignInUserId(qbSession.getUserId());
//
////                getFileList();
//                startSignIn();
//            }
//
//            @Override
//            public void onError(List<String> errors) {
//                // print errors that came from server
//                DialogUtils.showLong(context, errors.get(0));
//                progressBar.setVisibility(View.INVISIBLE);
//            }
//        });
    }

    private void createSession() {
        // Create QuickBlox session
        createSessionTask = new CreateSessionTask();
        createSessionTask.start(this);
    }

//    private void getFileList() {
//        if (DataHolder.getDataHolder().getSignInQbUser() != null) {
//            // Get all user's files
//            QBPagedRequestBuilder builder = new QBPagedRequestBuilder();
//            builder.setPerPage(Consts.QB_PER_PAGE);
//            builder.setPage(Consts.QB_PAGE);
//
//            QBContent.getFiles(builder, new QBEntityCallbackImpl<ArrayList<QBFile>>() {
//                @Override
//                public void onSuccess(ArrayList<QBFile> qbFiles, Bundle bundle) {
//                    DataHolder.getDataHolder().setQbFileList(qbFiles);
//                    // show activity_gallery
//                    startSignIn();
//                }
//
//                @Override
//                public void onError(List<String> strings) {
//                    Log.d("SplashActivity", "onError: " + strings);
//                }
//            });
//        }else{
//            startSignIn();
//        }
//    }

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

            //TODO: call getFileList

            startSignIn();
        }
    }

    @Override
    public void onFailed(String error) {

    }
}