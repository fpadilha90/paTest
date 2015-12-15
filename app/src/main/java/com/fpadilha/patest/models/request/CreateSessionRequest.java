package com.fpadilha.patest.models.request;

import com.fpadilha.patest.models.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by felipe on 12/12/2015.
 */
public class CreateSessionRequest {

    @SerializedName("application_id")
    private String applicationId; //API Application Identifier
//    private Integer applicationId; //API Application Identifier
    @SerializedName("auth_key")
    private String authKey; //Authentication Key
    private String timestamp; //Unix Timestamp It shouldn't be differ from time provided by NTP more than 10 minutes.
//    private long timestamp; //Unix Timestamp It shouldn't be differ from time provided by NTP more than 10 minutes.
    private String nonce; //Unique Random Value. Requests with the same timestamp and same value for nonce parameter can not be send twice.
//    private Integer nonce; //Unique Random Value. Requests with the same timestamp and same value for nonce parameter can not be send twice.
    private String signature; //auth_secret
    private User user;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
