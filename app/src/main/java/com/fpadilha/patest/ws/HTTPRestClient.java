package com.fpadilha.patest.ws;

import com.fpadilha.patest.models.request.CreateSessionRequest;
import com.fpadilha.patest.models.response.CreateSessionResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by felipe on 12/12/2015.
 */
public class HTTPRestClient implements RestClient {

    private static final String URL_BASE = "https://api.quickblox.com";
    private static final String URL_SESSION = URL_BASE +  "/session.json";
    private static final String URL_LOGIN = URL_BASE +  "/login.json";
    private static final String URL_USERS = URL_BASE +  "/users.json";
    private static final String URL_USER = URL_BASE +  "/users/%d.json";
    private static final String URL_DATA = URL_BASE +  "/data";

    private static final String HEADER_CONTENT_TYPE_PARAM = "Content-Type";
    private static final String HEADER_QB_TOKEN_PARAM = "QB-Token";
    private static final String HEADER_QB_API_VERSION_PARAM = "REST-API-Version";

    private static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    private static final String HEADER_QB_API_VERSION_VALUE = "0.1.0";

    private static final String GET = "GET";
    private static final String POST = "POST";

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
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setReadTimeout(DATA_RETRIEVAL_TIMEOUT);
            urlConnection.setRequestMethod(POST);
            urlConnection.setRequestProperty(HEADER_CONTENT_TYPE_PARAM, HEADER_CONTENT_TYPE_VALUE);
            urlConnection.setRequestProperty(HEADER_QB_API_VERSION_PARAM, HEADER_QB_API_VERSION_VALUE);


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

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;

    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }
}
