package com.fpadilha.patest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.fpadilha.patest.R;
import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.utils.Consts;
import com.fpadilha.patest.utils.DialogUtils;
import com.quickblox.content.QBContent;
import com.quickblox.content.model.QBFile;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;
import com.quickblox.core.request.QBPagedRequestBuilder;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.ArrayList;
import java.util.List;

import static com.fpadilha.patest.utils.Consts.APP_ID;
import static com.fpadilha.patest.utils.Consts.AUTH_KEY;
import static com.fpadilha.patest.utils.Consts.AUTH_SECRET;

public class SplashActivity extends Activity {

    private Context context;
    private ProgressBar progressBar;

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
        // Initialize QuickBlox application with credentials.
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);

        // Create QuickBlox session
        QBAuth.createSession(new QBEntityCallbackImpl<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {
//                DataHolder.getDataHolder().setSignInUserId(qbSession.getUserId());

//                getFileList();
                startSignIn();
            }

            @Override
            public void onError(List<String> errors) {
                // print errors that came from server
                DialogUtils.showLong(context, errors.get(0));
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
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

}