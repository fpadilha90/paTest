package com.fpadilha.patest.services;

import com.fpadilha.patest.models.response.BaseResponse;

import java.io.InputStream;

/**
 * Created by felipe on 12/12/2015.
 */
public interface DownloadFileCallback {
    void onFinish(InputStream inputStream);
    void onError(String error);
}
