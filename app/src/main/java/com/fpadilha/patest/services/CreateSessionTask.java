package com.fpadilha.patest.services;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Xml;

import com.fpadilha.patest.models.User;
import com.fpadilha.patest.models.request.CreateSessionRequest;
import com.fpadilha.patest.models.response.CreateSessionResponse;
import com.fpadilha.patest.utils.Consts;
import com.fpadilha.patest.ws.HTTPRestClient;
import com.fpadilha.patest.ws.RestClient;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.EncodedKeySpec;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by felipe on 12/12/2015.
 */
public class CreateSessionTask {

    private TaskCallback callback;
    private RestClient restClient;
    private User user;

    public CreateSessionTask() {
        restClient = new HTTPRestClient();
    }

    public void start(TaskCallback callback) {
        this.callback = callback;
        doInBackground();
    }

    public void start(TaskCallback callback, User user) {
        this.user = user;
        start(callback);
    }

    private void doInBackground() {
        new AsyncTask<Void, Void, CreateSessionResponse>() {
            @Override
            protected CreateSessionResponse doInBackground(Void... params) {
                CreateSessionRequest request = new CreateSessionRequest();
                request.setApplicationId(Consts.APP_ID);
                request.setAuthKey(Consts.AUTH_KEY);

                request.setTimestamp(String.valueOf(getUnixTime()));
                request.setNonce(String.valueOf(getRandomNumber()));

                request.setSignature(getSignature(String.valueOf(request.getNonce()), String.valueOf(request.getTimestamp())));

//                String tokenParams = "application_id=[appid]&auth_key=[authkey]&nonce=[nonce]&timestamp=[time]&signature=[sig]"
//                        .replace("[appid]", String.valueOf(Consts.APP_ID))
//                        .replace("[authkey]", Consts.AUTH_KEY)
//                        .replace("[nonce]", String.valueOf(request.getNonce()))
//                        .replace("[time]", String.valueOf(request.getTimestamp()))
//                        .replace("[sig]", GenerateSignature)

                request.setUser(user);

                CreateSessionResponse response = restClient.createSession(request);

                return response;
            }

            @Override
            protected void onPostExecute(CreateSessionResponse response) {

                callback.onSuccess(response);
            }
        }.execute();
    }

    public String getSignature(String nonce, String timestamp){

        String signatureParams = "application_id=[appid]&auth_key=[authkey]&nonce=[nonce]&timestamp=[time]"
                .replace("[appid]", String.valueOf(Consts.APP_ID))
                .replace("[authkey]", Consts.AUTH_KEY)
                .replace("[nonce]", nonce)
                .replace("[time]", timestamp);

        String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        SecretKeySpec signingKey = new SecretKeySpec(Consts.AUTH_SECRET.getBytes(), HMAC_SHA1_ALGORITHM);
        String result = null;
        try {
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(signatureParams.getBytes());
            result = new String(new Hex().encode(rawHmac), "UTF-8");
//            result = Base64.encodeToString(rawHmac, Base64.NO_WRAP);

        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;

    }

    public long getUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public Integer getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }
}
