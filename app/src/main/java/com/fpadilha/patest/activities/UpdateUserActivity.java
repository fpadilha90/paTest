package com.fpadilha.patest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.fpadilha.patest.R;
import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.utils.DialogUtils;
import com.fpadilha.patest.utils.GetImageFileTask;
import com.fpadilha.patest.utils.ImageHelper;
import com.fpadilha.patest.utils.OnGetImageFileListener;
import com.quickblox.content.QBContent;
import com.quickblox.content.model.QBFile;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBProgressCallback;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import java.io.File;
import java.io.InputStream;
import java.util.List;


public class UpdateUserActivity extends BaseActivity implements OnGetImageFileListener {

    private ProgressBar progressBar;
    private ProgressBar progressBarImage;
    private Button update;
    private FloatingActionButton changeAvatar;
    private LinearLayout container;
    private ImageView photo;
    private EditText password;
    private EditText repeatPassword;
    private EditText email;
    private EditText fullName;
    private EditText phone;
    private EditText webSite;
    private boolean onThread;
    private CollapsingToolbarLayout collapsingToolbar;

    private ImageHelper imageHelper;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_update_user);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inflateViews();
        fillAllFields();

        imageHelper = new ImageHelper(this);
        refreshAvatar();
    }

    private void refreshAvatar() {
        Integer userProfilePictureID = DataHolder.getDataHolder().getSignInQbUser().getFileId();

        if (userProfilePictureID != null) {
            progressBarImage.setVisibility(View.VISIBLE);
            changeAvatar.setEnabled(false);
            QBContent.downloadFileTask(userProfilePictureID, new QBEntityCallbackImpl<InputStream>() {
                @Override
                public void onSuccess(InputStream inputStream, Bundle params) {
                    new AsyncTask<InputStream, Void, Bitmap>() {
                        @Override
                        protected Bitmap doInBackground(InputStream... params) {
                            return BitmapFactory.decodeStream(params[0]);
                        }

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            photo.setImageBitmap(bitmap);
                            progressBarImage.setVisibility(View.GONE);
                            changeAvatar.setEnabled(true);
                        }
                    }.execute(inputStream);
                }

                @Override
                public void onError(List<String> errors) {
                    progressBarImage.setVisibility(View.GONE);
                    changeAvatar.setEnabled(true);
                }
            }, new QBProgressCallback() {
                @Override
                public void onProgressUpdate(int progress) {

                }
            });
        }
    }

    private void inflateViews() {
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        changeAvatar = (FloatingActionButton) findViewById(R.id.changeAvatar);
        update = (Button) findViewById(R.id.update);
        progressBarImage = (ProgressBar) findViewById(R.id.progressBarImage);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        container = (LinearLayout) findViewById(R.id.container);
        photo = (ImageView) findViewById(R.id.photo);
        password = (EditText) findViewById(R.id.password);
        repeatPassword = (EditText) findViewById(R.id.repeatPassword);
        email = (EditText) findViewById(R.id.email);
        fullName = (EditText) findViewById(R.id.fullName);
        phone = (EditText) findViewById(R.id.phone);
        webSite = (EditText) findViewById(R.id.webSite);
    }

    private void fillAllFields() {
        collapsingToolbar.setTitle(DataHolder.getDataHolder().getSignInUserLogin());

        email.setText(DataHolder.getDataHolder().getSignInUserEmail());
        fullName.setText(DataHolder.getDataHolder().getSignInUserFullName());
        phone.setText(DataHolder.getDataHolder().getSignInUserPhone());
        webSite.setText(DataHolder.getDataHolder().getSignInUserWebSite());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            onThread = true;
            publishProgress();
            Uri originalUri = data.getData();
            photo.setImageURI(originalUri);

            new GetImageFileTask(this).execute(imageHelper, photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update:
                if (validate()) {
                    onThread = true;
                    publishProgress();

                    // Update user
                    QBUser qbUser = new QBUser();
                    if (DataHolder.getDataHolder().getSignInUserId() != -1) {
                        qbUser.setId(DataHolder.getDataHolder().getSignInUserId());
                    }
                    qbUser.setLogin(DataHolder.getDataHolder().getSignInUserLogin());

                    if (!"".equals(password.getText().toString())) {
                        qbUser.setPassword(password.getText().toString());
                        qbUser.setOldPassword(DataHolder.getDataHolder().getSignInUserOldPassword());
                    }
                    qbUser.setFullName(fullName.getText().toString());
                    qbUser.setEmail(email.getText().toString());
                    qbUser.setPhone(phone.getText().toString());
                    qbUser.setWebsite(webSite.getText().toString());

                    QBUsers.updateUser(qbUser, new QBEntityCallbackImpl<QBUser>() {
                        @Override
                        public void onSuccess(QBUser qbUser, Bundle bundle) {

                            onThread = false;
                            publishProgress();

                            DataHolder.getDataHolder().setSignInQbUser(qbUser);
                            if (!"".equals(password.getText().toString())) {
                                DataHolder.getDataHolder().setSignInUserPassword(
                                        password.getText().toString());
                            }
                            DialogUtils.showLong(context, getResources().getString(
                                    R.string.success_user_updated));
                        }

                        @Override
                        public void onError(List<String> strings) {
                            onThread = false;
                            publishProgress();
                            DialogUtils.showLong(context, strings.get(0));
                        }
                    });
                }
                break;
            case R.id.changeAvatar:
                imageHelper.getImage();
                break;
        }
    }

    private void publishProgress() {
        progressBar.setVisibility((onThread ? View.VISIBLE : View.GONE));
        update.setVisibility((!onThread ? View.VISIBLE : View.GONE));
        container.setEnabled(!onThread);
        changeAvatar.setEnabled(!onThread);
    }

    private boolean validate() {
        boolean valid = true;

        if (!password.getText().toString().equals(repeatPassword.getText().toString())) {
            valid = false;
            repeatPassword.setError(getString(R.string.err_password_dont_match));
            DialogUtils.showLong(context, getString(R.string.err_password_dont_match));
        }

        return valid;
    }

    @Override
    public void onGotImageFile(File imageFile) {
        uploadSelectedImage(imageFile);
    }


    private void uploadSelectedImage(File imageFile) {

        // Upload new file
        QBContent.uploadFileTask(imageFile, false, null, new QBEntityCallbackImpl<QBFile>() {
            @Override
            public void onSuccess(QBFile qbFile, Bundle params) {

                int uploadedFileID = qbFile.getId();
                QBUser qbUser = DataHolder.getDataHolder().getSignInQbUser();

                // Connect image to user
                qbUser.setFileId(uploadedFileID);

                QBUsers.updateUser(qbUser, new QBEntityCallbackImpl<QBUser>() {
                    @Override
                    public void onSuccess(QBUser user, Bundle args) {
                        onThread = false;
                        publishProgress();
                        DialogUtils.showLong(context, getString(R.string.success_user_updated));
                    }

                    @Override
                    public void onError(List<String> errors) {
                        onThread = false;
                        publishProgress();
                        DialogUtils.showLong(context, errors.get(0));
                    }
                });
            }

            @Override
            public void onError(List<String> errors) {
                onThread = false;
                publishProgress();
                DialogUtils.showLong(context, errors.get(0));

            }
        }, new QBProgressCallback() {
            @Override
            public void onProgressUpdate(int progress) {


            }
        });

    }
}