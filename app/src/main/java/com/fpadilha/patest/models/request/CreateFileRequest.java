package com.fpadilha.patest.models.request;

import com.fpadilha.patest.models.Blob;

/**
 * Created by felipe on 22/12/2015.
 */
public class CreateFileRequest {

    private Blob blob;

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }
}
