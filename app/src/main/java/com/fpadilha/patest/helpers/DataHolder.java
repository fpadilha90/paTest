package com.fpadilha.patest.helpers;


import com.fpadilha.patest.models.QBUser;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {

    private static DataHolder dataHolder;
//    private List<QBUser> qbUsersList = new ArrayList<QBUser>();
    private QBUser signInQbUser;
    private String token;
//    private List<QBFile> qbFileList;

    public static synchronized DataHolder getDataHolder() {
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
        return dataHolder;
    }

    public void setSignInQbUser(QBUser signInQbUser) {
        this.signInQbUser = signInQbUser;
    }

    public QBUser getSignInQbUser() {
        return signInQbUser;
    }
    //
//    public void setQbFileList(List<QBFile> qbFileList) {
//        this.qbFileList = qbFileList;
//    }

//    public int getQbFileListSize() {
//        if (qbFileList == null) return 0;
//        return qbFileList.size();
//    }

//    public String getUrl(int position) {
        // URL formation documentation
        // http://quickblox.com/developers/Content#API_Content_Get_File_As_A_Redirect_To_The_S3_Object

//        String sessionToken = null;
//        try {
//            sessionToken = BaseService.getBaseService().getToken();
//        } catch (BaseServiceException e) {
//            e.printStackTrace();
//        }
//
//        return BaseService.getServiceEndpointURL() + "/blobs/" + qbFileList.get(position).getUid() +
//                "?token=" + sessionToken;
//    }
//
//
//    public void setSignInQbUser(QBUser singInQbUser) {
//        this.signInQbUser = singInQbUser;
//    }
//
//    public String getSignInUserOldPassword() {
//        return signInQbUser.getOldPassword();
//    }
//
//    public int getSignInUserId() {
//        return signInQbUser.getId();
//    }
//
//    public void setSignInUserPassword(String singInUserPassword) {
//        signInQbUser.setOldPassword(singInUserPassword);
//    }
//
//    public String getSignInUserLogin() {
//        return signInQbUser.getLogin();
//    }
//
//    public String getSignInUserEmail() {
//        return signInQbUser.getEmail();
//    }
//
//    public String getSignInUserFullName() {
//        return signInQbUser.getFullName();
//    }
//
//    public String getSignInUserPhone() {
//        return signInQbUser.getPhone();
//    }
//
//    public String getSignInUserWebSite() {
//        return signInQbUser.getWebsite();
//    }
//
//    public void addQbFile(QBFile qbFile) {
//        if (qbFileList == null) qbFileList = new ArrayList<>();
//        qbFileList.add(qbFile);
//    }


}
