package com.fpadilha.patest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by felipe on 12/12/2015.
 */
public class User {

    private User(Builder builder) {
        this.id = builder.id ;
        this.ownerId = builder.ownerId;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.login = builder.login;
        this.phone = builder.phone;
        this.website = builder.website;
        this.password = builder.password;
        this.oldPassword = builder.oldPassword;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.lastRequestAt = builder.lastRequestAt;
        this.externalUserId = builder.externalUserId;
        this.facebookId = builder.facebookId;
        this.twitterId = builder.twitterId;
        this.blobId = builder.blobId;
        this.customData = builder.customData;
        this.userTags = builder.userTags;
        this.tagList = builder.tagList;
    }

    private Integer id;
    @SerializedName("owner_id")
    private Integer ownerId;
    @SerializedName("full_name")
    private String fullName;
    private String email;
    private String login;
    private String phone;
    private String website;
    private String password;
    private String oldPassword;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("last_request_at")
    private String lastRequestAt;
    @SerializedName("external_user_id")
    private Integer externalUserId;
    @SerializedName("facebook_id")
    private String facebookId;
    @SerializedName("twitter_id")
    private String twitterId;
    @SerializedName("blob_id")
    private String blobId;
    @SerializedName("custom_data")
    private String customData;
    @SerializedName("user_tags")
    private String userTags;
    @SerializedName("tag_list")
    private String tagList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastRequestAt() {
        return lastRequestAt;
    }

    public void setLastRequestAt(String lastRequestAt) {
        this.lastRequestAt = lastRequestAt;
    }

    public Integer getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(Integer externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public String getBlobId() {
        return blobId;
    }

    public void setBlobId(String blobId) {
        this.blobId = blobId;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public String getUserTags() {
        return userTags;
    }

    public void setUserTags(String userTags) {
        this.userTags = userTags;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public static class Builder {

        public Builder() {
        }

        public User build() {
            return new User(this);
        }

        private Integer id;
        private Integer ownerId;
        private String fullName;
        private String email;
        private String login;
        private String phone;
        private String website;
        private String password;
        private String oldPassword;
        private String createdAt;
        private String updatedAt;
        private String lastRequestAt;
        private Integer externalUserId;
        private String facebookId;
        private String twitterId;
        private String blobId;
        private String customData;
        private String userTags;
        private String tagList;

        public User.Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public User.Builder setOwnerId(Integer ownerId) {
            this.ownerId = ownerId;
            return this;
        }

        public User.Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public User.Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User.Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public User.Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public User.Builder setWebsite(String website) {
            this.website = website;
            return this;
        }

        public User.Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User.Builder setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
            return this;
        }

        public User.Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User.Builder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User.Builder setLastRequestAt(String lastRequestAt) {
            this.lastRequestAt = lastRequestAt;
            return this;
        }

        public User.Builder setExternalUserId(Integer externalUserId) {
            this.externalUserId = externalUserId;
            return this;
        }

        public User.Builder setFacebookId(String facebookId) {
            this.facebookId = facebookId;
            return this;
        }

        public User.Builder setTwitterId(String twitterId) {
            this.twitterId = twitterId;
            return this;
        }

        public User.Builder setBlobId(String blobId) {
            this.blobId = blobId;
            return this;
        }

        public User.Builder setCustomData(String customData) {
            this.customData = customData;
            return this;
        }

        public User.Builder setUserTags(String userTags) {
            this.userTags = userTags;
            return this;
        }

        public User.Builder setTagList(String tagList) {
            this.tagList = tagList;
            return this;
        }
    }

}
