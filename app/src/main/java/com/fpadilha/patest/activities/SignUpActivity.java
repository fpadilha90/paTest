package com.fpadilha.patest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.fpadilha.patest.R;
import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.utils.DialogUtils;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.util.List;

public class SignUpActivity extends BaseActivity {

    private EditText login;
    private EditText password;
    private EditText repeatPassword;
    private Button complete;
    private ProgressBar progressBar;

    boolean onThread;

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
            QBUser user = new QBUser(login.getText().toString(), password.getText().toString());

            QBUsers.signUp(user, new QBEntityCallbackImpl<QBUser>() {
                @Override
                public void onSuccess(QBUser user, Bundle args) {
                    setResult(RESULT_OK);

                    DataHolder.getDataHolder().setSignInQbUser(user);
                    DataHolder.getDataHolder().setSignInUserPassword(password.getText().toString());

                    signIn(user);

                }

                @Override
                public void onError(List<String> errors) {
                    onThread = false;
                    publishProgress();
                    DialogUtils.showLong(context, errors.get(0));
                }
            });
        }
    }

    private void signIn(QBUser qbUser) {
        QBUsers.signIn(qbUser, new QBEntityCallbackImpl<QBUser>() {
            @Override
            public void onSuccess(QBUser qbUser, Bundle bundle) {
                setResult(RESULT_OK);

                DataHolder.getDataHolder().setSignInQbUser(qbUser);
                DataHolder.getDataHolder().setSignInUserPassword(password.getText().toString());

                DialogUtils.show(context, getString(R.string.success_user_signup));
                startActivity(new Intent(context, UpdateUserActivity.class));
                finish();
            }

            @Override
            public void onError(List<String> errors) {
                onThread = false;
                publishProgress();
                DialogUtils.showLong(context, errors.get(0));
                finish();
            }
        });
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

}