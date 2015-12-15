package com.fpadilha.patest.services;

import com.fpadilha.patest.models.response.BaseResponse;

/**
 * Created by felipe on 12/12/2015.
 */
public interface TaskCallback {
    void onSuccess(BaseResponse response);
    void onFailed(String error);
}
