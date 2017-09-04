package com.kkk.httptrequest;

import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by xiaohe on 17-9-4.
 */

public class HttpControl {
    private HttpMethod method;
    private HttpResponse getResponseFromUrl(String httpUrl){
        HttpResponse response = new HttpResponse();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            connection.connect();
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void setRequestMethod(HttpMethod method){
        this.method = method;
    }
    public enum HttpMethod{
        GET,POST
    }
}
