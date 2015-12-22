package com.fpadilha.patest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by felipe on 22/12/2015.
 */
public class Blob {
    @SerializedName("blob_status")
    private String blobStatus;
    @SerializedName("content_type")
    private String contentType;
    @SerializedName("created_at")
    private String createdAt;
    private Integer id;
    @SerializedName("last_read_access_ts")
    private String lastReadAccessTs;
    @SerializedName("lifetime")
    private Integer lifeTime;
    private String name;
    @SerializedName("public")
    private Boolean publicAccess;
    @SerializedName("ref_count")
    private Integer refCount;
    @SerializedName("set_completed_at")
    private String setCompletedAt;
    private String size;
    private String uid;
    @SerializedName("updated_at")
    private String updateAt;
    @SerializedName("blob_object_access")
    private BlobObjectAccess blobObjectAccess;

    public String getBlobStatus() {
        return blobStatus;
    }

    public void setBlobStatus(String blobStatus) {
        this.blobStatus = blobStatus;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastReadAccessTs() {
        return lastReadAccessTs;
    }

    public void setLastReadAccessTs(String lastReadAccessTs) {
        this.lastReadAccessTs = lastReadAccessTs;
    }

    public Integer getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(Integer lifeTime) {
        this.lifeTime = lifeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublicAccess() {
        return publicAccess;
    }

    public void setPublicAccess(Boolean publicAccess) {
        this.publicAccess = publicAccess;
    }

    public Integer getRefCount() {
        return refCount;
    }

    public void setRefCount(Integer refCount) {
        this.refCount = refCount;
    }

    public String getSetCompletedAt() {
        return setCompletedAt;
    }

    public void setSetCompletedAt(String setCompletedAt) {
        this.setCompletedAt = setCompletedAt;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public BlobObjectAccess getBlobObjectAccess() {
        return blobObjectAccess;
    }

    public void setBlobObjectAccess(BlobObjectAccess blobObjectAccess) {
        this.blobObjectAccess = blobObjectAccess;
    }
}
