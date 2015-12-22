package com.fpadilha.patest.ws;

import android.content.Context;

import com.fpadilha.patest.helpers.DataHolder;
import com.fpadilha.patest.models.Blob;
import com.fpadilha.patest.models.BlobObjectAccess;
import com.fpadilha.patest.models.request.CreateFileRequest;
import com.fpadilha.patest.models.request.CreateSessionRequest;
import com.fpadilha.patest.models.request.SignInRequest;
import com.fpadilha.patest.models.request.SignUpRequest;
import com.fpadilha.patest.models.request.UpdateUserRequest;
import com.fpadilha.patest.models.response.CreateFileResponse;
import com.fpadilha.patest.models.response.CreateSessionResponse;
import com.fpadilha.patest.models.response.SignInResponse;
import com.fpadilha.patest.models.response.SignUpResponse;
import com.fpadilha.patest.models.response.UpdateUserResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by felipe on 12/12/2015.
 */
public class HTTPRestClient implements RestClient {
    private static final int BUFFER_SIZE = 4096;

    private static final String URL_BASE = "https://api.quickblox.com";
    private static final String URL_SESSION = URL_BASE + "/session.json";
    private static final String URL_LOGIN = URL_BASE + "/login.json";
    private static final String URL_USERS = URL_BASE + "/users.json";
    private static final String URL_USER = URL_BASE + "/users/%d.json";
    private static final String URL_DATA = URL_BASE + "/data";
    private static final String URL_BLOB = URL_BASE + "/blobs.json";
    private static final String URL_BLOB_DOWNLOAD = URL_BASE + "/blobs/%d/download.json";
    private static final String URL_UPLOAD_FILE = "https://qbprod.s3.amazonaws.com/";
    private static final String URL_COMPLETE_UPLOAD = URL_BASE + "/blobs/%d/complete.json";

    private static final String HEADER_CONTENT_TYPE_PARAM = "Content-Type";
    private static final String HEADER_QB_TOKEN_PARAM = "QB-Token";
    private static final String HEADER_QB_API_VERSION_PARAM = "QuickBlox-REST-API-Version";

    private static final String HEADER_CONTENT_TYPE_JSON = "application/json";
    private static final String HEADER_CONTENT_TYPE_IMAGE = "image/jpeg";
    private static final String HEADER_QB_API_VERSION_VALUE = "0.1.0";

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";

    private static final int CONNECTION_TIMEOUT = 2000;
    private static final int DATA_RETRIEVAL_TIMEOUT = 2000;

    @Override
    public CreateSessionResponse createSession(CreateSessionRequest request) {

        HttpURLConnection urlConnection = null;
        DataOutputStream printout;
        try {
            URL url = new URL(URL_SESSION);

            urlConnection = (HttpURLConnection)
                    url.openConnection();

            setHeader(urlConnection, POST, false);

            String str = new Gson().toJson(request);
            byte[] data = str.getBytes("UTF-8");
            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();


            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new Gson().fromJson(getResponseText(in), CreateSessionResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;

    }


    @Override
    public SignUpResponse signUp(SignUpRequest request) {

        HttpURLConnection urlConnection = null;
        DataOutputStream printout;
        try {
            URL url = new URL(URL_USERS);

            urlConnection = (HttpURLConnection)
                    url.openConnection();

            setHeader(urlConnection, POST, true);

            String str = new Gson().toJson(request);
            byte[] data = str.getBytes("UTF-8");
            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();


            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new Gson().fromJson(getResponseText(in), SignUpResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }


    @Override
    public SignInResponse signIn(SignInRequest request) {

        HttpURLConnection urlConnection = null;
        DataOutputStream printout;
        try {
            URL url = new URL(URL_LOGIN);

            urlConnection = (HttpURLConnection)
                    url.openConnection();

            setHeader(urlConnection, POST, true);

            String str = new Gson().toJson(request);
            byte[] data = str.getBytes("UTF-8");
            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();


            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new Gson().fromJson(getResponseText(in), SignInResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;

    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request) {

        HttpURLConnection urlConnection = null;
        DataOutputStream printout;
        try {
            URL url = new URL(String.format(URL_USER, DataHolder.getDataHolder().getSignInUser().getId()));

            urlConnection = (HttpURLConnection)
                    url.openConnection();

            setHeader(urlConnection, PUT, true);

            String str = new Gson().toJson(request);
            byte[] data = str.getBytes("UTF-8");
            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();


            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new Gson().fromJson(getResponseText(in), UpdateUserResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    @Override
    public InputStream downloadFile(Integer userProfilePictureID) {

        HttpURLConnection urlConnection;
        try {
            URL url = new URL(String.format(URL_BLOB_DOWNLOAD, userProfilePictureID));

            urlConnection = (HttpURLConnection)
                    url.openConnection();

            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
            urlConnection.setRequestMethod(GET);
            urlConnection.setRequestProperty(HEADER_QB_API_VERSION_PARAM, HEADER_QB_API_VERSION_VALUE);

            urlConnection.setRequestProperty(HEADER_QB_TOKEN_PARAM, DataHolder.getDataHolder().getToken());

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // opens input stream from the HTTP connection
                return urlConnection.getInputStream();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public InputStream uploadFile(File imageFile) {

        HttpURLConnection urlConnection = null;
        DataOutputStream printout;
        try {
            URL url = new URL(URL_BLOB);

            urlConnection = (HttpURLConnection)
                    url.openConnection();

            setHeader(urlConnection, POST, true);

            CreateFileRequest createFileRequest = new CreateFileRequest();
            Blob blob = new Blob();
            blob.setContentType(HEADER_CONTENT_TYPE_IMAGE);
            blob.setName("profile");
            createFileRequest.setBlob(blob);

            String str = new Gson().toJson(createFileRequest);
            byte[] data = str.getBytes("UTF-8");
            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.write(data);
            printout.flush();
            printout.close();


            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            CreateFileResponse createFileResponse = new Gson().fromJson(getResponseText(in), CreateFileResponse.class);

            if (createFileResponse != null){
                url = new URL(URL_UPLOAD_FILE);

                urlConnection = (HttpURLConnection)
                        url.openConnection();

                urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
                urlConnection.setRequestMethod(POST);
                urlConnection.setRequestProperty(HEADER_CONTENT_TYPE_PARAM, HEADER_CONTENT_TYPE_IMAGE);

                blob = createFileResponse.getBlob();
                BlobObjectAccess boa = blob.getBlobObjectAccess();

                urlConnection.setRequestProperty("Expires", boa.getExpires());
                urlConnection.setRequestProperty("acl", "authenticated-read");
                urlConnection.setRequestProperty("key", "HfhCE5dNGHFeZR9");
                urlConnection.setRequestProperty("policy", "eyJleHBpcmF0aW9uIjoiMjAxNS0wOS0zMFQxMzoyOTozOVoiLCJjb25kaXRpb25zIjpbeyJidWNrZXQiOiJxYnByb2QifSx7ImFjbCI6ImF1dGhlbnRpY2F0ZWQtcmVhZCJ9LHsiQ29udGVudC1UeXBlIjoiaW1hZ2UvanBnIn0seyJzdWNjZXNzX2FjdGlvbl9zdGF0dXMiOiIyMDEifSx7IkV4cGlyZXMiOiJXZWQsIDMwIFNlcCAyMDE1IDEzOjI5OjM5IEdNVCJ9LHsia2V5IjoiNzYxMDFlZGQ4N2ZlNGIyOTlmZjQxZjYzNjMzYmY5YzEwMCJ9LHsieC1hbXotY3JlZGVudGlhbCI6IkFLSUFJWTdLRk0yM1hHWEo3UjdBLzIwMTUwOTMwL3VzLWVhc3QtMS9zMy9hd3M0X3JlcXVlc3QifSx7IngtYW16LWFsZ29yaXRobSI6IkFXUzQtSE1BQy1TSEEyNTYifSx7IngtYW16LWRhdGUiOiIyMDE1MDkzMFQxMjI5MzlaIn1dfQ==");
                urlConnection.setRequestProperty("success_action_status", "201");
                urlConnection.setRequestProperty("x-amz-algorithm", "AWS4-HMAC-SHA256");
                urlConnection.setRequestProperty("x-amz-credential", "AKIAIY7KFM23XGXJ7R7A/20150930/us-east-1/s3/aws4_request");
                urlConnection.setRequestProperty("x-amz-date", "20150930T122939Z");
                urlConnection.setRequestProperty("x-amz-signature", "eee18ae3d47a745bccc9007d1b7b1679e855becb44b1928bb710428e18e397a8");
                urlConnection.setRequestProperty("file", blob.getName());



            }

            return  null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;

    }

    private void setHeader(HttpURLConnection urlConnection, String method, boolean token) throws ProtocolException {
        urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
        urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
        urlConnection.setRequestMethod(method);
        urlConnection.setRequestProperty(HEADER_CONTENT_TYPE_PARAM, HEADER_CONTENT_TYPE_JSON);
        urlConnection.setRequestProperty(HEADER_QB_API_VERSION_PARAM, HEADER_QB_API_VERSION_VALUE);

        if (token) {
            urlConnection.setRequestProperty(HEADER_QB_TOKEN_PARAM, DataHolder.getDataHolder().getToken());
        }

    }

    private static String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
