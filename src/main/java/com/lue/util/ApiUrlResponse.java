package com.lue.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author lue
 */
public class ApiUrlResponse {

    public String finalResult = "";
    public String finalVideoResult = "";
    
    public void loginApiUrl(String api_url, String Username, String Password) throws IOException, JSONException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(api_url);

        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        JSONObject objtoken = new JSONObject();
        objtoken.put("Username", Username);
        objtoken.put("Password", Password);

        StringEntity entity = new StringEntity(objtoken.toString());
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        finalResult = result.toString();
        
    }
    public void videoApiUrl(String api_url,String LicenceUserId) throws IOException, JSONException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(api_url);

        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        JSONObject objtoken = new JSONObject();
        objtoken.put("LicenceUserId", LicenceUserId);
        
        StringEntity entity = new StringEntity(objtoken.toString());
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        finalVideoResult=result.toString();
       
    }
}
