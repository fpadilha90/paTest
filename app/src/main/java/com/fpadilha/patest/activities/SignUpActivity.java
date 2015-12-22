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
import com.fpadilha.patest.models.response.SignUpResponse;
import com.fpadilha.patest.services.SignInTask;
import com.fpadilha.patest.services.SignUpTask;
import com.fpadilha.patest.services.TaskCallback;
import com.fpadilha.patest.utils.DialogUtils;

import java.util.List;

public class SignUpActivity extends BaseActivity implements TaskCallback {

    private EditText login;
    private EditText password;
    private EditText repeatPassword;
    private Button complete;
    private ProgressBar progressBar;

    boolean onThread;

    private SignUpTask signUpTask;
    private SignInTask signInTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflateViews();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.complete:
                completeSignUp();
                break;
        }
    }

    private void inflateViews() {
        setContentView(R.layout.activity_sign_up);

        login = ((EditText) findViewById(R.id.login));
        password = ((EditText) findViewById(R.id.password));
        repeatPassword = ((EditText) findViewById(R.id.repeatPassword));
        complete = ((Button) findViewById(R.id.complete));
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void completeSignUp() {
        if (validate()) {
            onThread = true;
            publishProgress();

            User user = new User();
            user.setLogin(login.getText().toString());
            user.setPassword(password.getText().toString());

            signUpTask = new SignUpTask();
            signUpTask.start(this, user);
        }
    }

    private void signIn(User user) {
        signInTask = new SignInTask();
        signInTask.start(this, user);
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
        if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
            valid = false;
            repeatPassword.setError(getString(R.string.err_password_dont_match));
        }

        return valid;
    }

    private void publishProgress() {
        progressBar.setVisibility((onThread ? View.VISIBLE : View.GONE));
        complete.setVisibility((!onThread ? View.VISIBLE : View.GONE));
        login.setEnabled(!onThread);
        password.setEnabled(!onThread);
        repeatPassword.setEnabled(!onThread);
    }

    @Override public void onSuccess(BaseResponse response) {
//        setResult(RESULT_OK);
        if (response instanceof SignUpResponse) {
            User user = ((SignUpResponse) response).getUser();
            user.setPassword(password.getText().toString());

            DataHolder.getDataHolder().setSignInUser(user);

            signIn(user);
        } else if (response instanceof SignInResponse) {
            SignInResponse signInResponse = (SignInResponse) response;

            User user = signInResponse.getUser();

            user.setPassword(password.getText().toString());
            DataHolder.getDataHolder().setSignInUser(user);

            DialogUtils.show(context, getString(R.string.success_user_signup));
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