package com.fpadilha.patest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by felipe on 22/12/2015.
 */
public class BlobObjectAccess {
    @SerializedName("blob_id")
    private Integer blobId;
    private String expires;
    private Integer id;
    @SerializedName("object_access_type")
    private String objectAccessType;
    private String params;

    public Integer getBlobId() {
        return blobId;
    }

    public void setBlobId(Integer blobId) {
        this.blobId = blobId;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectAccessType() {
        return objectAccessType;
    }

    public void setObjectAccessType(String objectAccessType) {
        this.objectAccessType = objectAccessType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
