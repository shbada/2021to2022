package com.test.technology.commons;

import okhttp3.*;

import java.io.IOException;

public class HttpConnection {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    /**
     * 동기방식 :POST
     * @param requestURL
     * @param jsonMessage
     */
    public void synPost(String requestURL, String jsonMessage) {
        try{
            Request request = new Request.Builder()
                    .url(requestURL)
                    .post(RequestBody.create(JSON, jsonMessage))
                    .build();

            Response response = client.newCall(request).execute();

            String message = response.body().string();
            System.out.println(message);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * 비동기방식: POST
     * @param requestURL
     * @param message
     */
    public void post(String requestURL, String message) {
        try{
            Request request = new Request.Builder()
                    .url(requestURL)
                    .post(RequestBody.create(JSON, message))
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("error + Connect Server Error is " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("Response Body is " + response.body().string());
                }
            });

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * 동기방식 :GET
     * @param requestURL
     */
    public void SynGet(String requestURL) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(requestURL)
                    .build();

            Response response = client.newCall(request).execute();

            String message = response.body().string();
            System.out.println(message);
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }

    /**
     * 비동기방식 :GET
     * @param requestURL
     */
    public void get(String requestURL) {
        try {
            Request request = new Request.Builder()
                    .url(requestURL)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("error + Connect Server Error is " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("Response Body is " + response.body().string());
                }
            });

        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
