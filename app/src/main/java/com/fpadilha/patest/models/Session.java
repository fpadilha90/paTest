package com.fpadilha.patest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by felipe on 12/12/2015.
 */
public class Session {

    @SerializedName("application_id")
    private Integer applicationId;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("device_id")
    private String deviceId;
    private Integer id;
    private Integer nonce;
    private String token;
    private Integer ts;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("user_id")
    private Integer userId;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
