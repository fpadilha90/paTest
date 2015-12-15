package com.fpadilha.patest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.fpadilha.patest.R;
import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.utils.Consts;
import com.fpadilha.patest.utils.DialogUtils;

import java.util.List;

public class SignInActivity extends BaseActivity {

    private EditText login;
    private EditText password;
    private Button signIn;
    private Button signUp;
    private ProgressBar progressBar;
    private boolean onThread;

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
                if (validate()) {

                    // Sign in application with user
//                    QBUser qbUser = new QBUser(login.getText().toString(), password.getText().toString());
//                    QBUsers.signIn(qbUser, new QBEntityCallbackImpl<QBUser>() {
//                        @Override
//                        public void onSuccess(QBUser qbUser, Bundle bundle) {
//                            setResult(RESULT_OK);
//
//                            DataHolder.getDataHolder().setSignInQbUser(qbUser);
//                            DataHolder.getDataHolder().setSignInUserPassword(password.getText().toString());
//
//                            startActivity(new Intent(context, UpdateUserActivity.class));
//                            finish();
//                        }
//
//                        @Override
//                        public void onError(List<String> errors) {
//                            onThread = false;
//                            publishProgress();
//                            DialogUtils.showLong(context, errors.get(0));
//                        }
//                    });
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
}