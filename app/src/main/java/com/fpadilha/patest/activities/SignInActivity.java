package com.fpadilha.patest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.fpadilha.patest.R;
import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.models.User;
import com.fpadilha.patest.models.response.BaseResponse;
import com.fpadilha.patest.models.response.SignInResponse;
import com.fpadilha.patest.services.SignInTask;
import com.fpadilha.patest.services.TaskCallback;
import com.fpadilha.patest.utils.Consts;
import com.fpadilha.patest.utils.DialogUtils;

import java.util.List;

public class SignInActivity extends BaseActivity implements TaskCallback {

    private EditText login;
    private EditText password;
    private Button signIn;
    private Button signUp;
    private ProgressBar progressBar;
    private boolean onThread;

    private SignInTask signInTask;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        inflateViews();

    }

    private void inflateViews() {
        setContentView(R.layout.activity_sign_in);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        signIn = (Button) findViewById(R.id.signIn);
        signUp = (Button) findViewById(R.id.signUp);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn:
                login.setText("fpadilha90");
                password.setText("acbedfgh");
                if (validate()) {
                    User user = new User();
                    user.setLogin(login.getText().toString());
                    user.setPassword(password.getText().toString());

                    // Sign in application with user
                    signInTask = new SignInTask();
                    signInTask.start(this, user);
                }
                break;
            case R.id.signUp:
                startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    private boolean validate() {
        boolean valid = true;

        if (login.length() == 0) {
            valid = false;
            login.setError(getString(R.string.err_invalid_login));
        }
        if (password.length() == 0) {
            valid = false;
            password.setError(getString(R.string.err_invalid_password));
        }

        return valid;
    }

    private void publishProgress() {
        progressBar.setVisibility((onThread ? View.VISIBLE : View.GONE));
        signIn.setVisibility((!onThread ? View.VISIBLE : View.GONE));
        login.setEnabled(!onThread);
        password.setEnabled(!onThread);
        signUp.setEnabled(!onThread);
    }

    @Override public void onSuccess(BaseResponse response) {
        if (response instanceof SignInResponse) {

            SignInResponse signInResponse = (SignInResponse) response;
            User user = signInResponse.getUser();
            DataHolder.getDataHolder().setSignInUser(user);

            startActivity(new Intent(context, UpdateUserActivity.class));
            finish();

        }
    }

    @Override public void onFailed(String error) {
        onThread = false;
        publishProgress();
        DialogUtils.showLong(context, error);
    }
}