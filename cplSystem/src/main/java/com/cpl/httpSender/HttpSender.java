package com.cpl.httpSender;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hp on 2016/5/31.
 */
public class HttpSender {
    private static String URLHEAD = "http://202.120.40.111:9090/api/v3/";

    public JSONArray getRequest(String url, String param){
        BufferedReader in = null;
        String result = "";

        try{
            String urlString = URLHEAD + url + "?" + param;
            System.out.println("get url:" + urlString);
            URL realUrl = new URL(urlString);

            URLConnection conn = realUrl.openConnection();
            conn.connect();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while((line = in.readLine()) != null){
                result += line;
            }

            return getJSArray(result);

        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public JSONArray postRequest(String url, String param){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try{
            URL realUrl = new URL(URLHEAD + url);
            System.out.println("post url:" + realUrl);
            URLConnection conn = realUrl.openConnection();

            //for post method
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());
            //System.out.println("post:" + param);
            out.print(param);
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = in.readLine()) != null){
                result += line;
            }

            return getJSArray(result);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void putRequest(String _url, String param){
        URL url = null;
        try {
            url = new URL(URLHEAD + _url);
            System.out.println("put:" + url);

        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }
        HttpURLConnection conn = null;
        PrintWriter out = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            out = new PrintWriter(conn.getOutputStream());
            System.out.println("put:" + param);
            out.write(param);

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();

                    DataInputStream in = new DataInputStream(conn.getInputStream());
                    System.out.println(in.toString());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public void deleteRequest(String _url, String param){
        URL url = null;
        try {
            url = new URL(URLHEAD + _url + "?" + param);
            System.out.println(url);
        } catch (MalformedURLException exception) {
            exception.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            System.out.println(conn.getResponseCode());
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            //try{
            //    DataInputStream in = new DataInputStream(conn.getInputStream());
            //    System.out.println(in.toString());
            //} catch (IOException exception) {
            //    exception.printStackTrace();
            //}
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private JSONArray getJSArray(String result){
        if(result.charAt(0) == '['){
            JSONArray jsArray = new JSONArray(result);
            return jsArray;
        }else{
            JSONObject jsObject = new JSONObject(result);
            JSONArray jsArray = new JSONArray();
            jsArray.put(jsObject);
            return jsArray;
        }
    }
}
