package com.test.technology.commons;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class HttpConnection {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    /**
     * 동기방식 :POST
     * @param requestURL
     * @param jsonMessage
     */
    public void syncPost(String requestURL, String jsonMessage) {
        try{
            Request request = new Request.Builder()
                    .url(requestURL)
                    .post(RequestBody.create(JSON, jsonMessage))
                    .build();

            Response response = client.newCall(request).execute();

            String message = response.body().string();
            log.info(message);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * 비동기방식: POST
     * @param requestURL
     * @param jsonMessage
     */
    public void post(String requestURL, String jsonMessage) {
        try{
            Request request = new Request.Builder()
                    .url(requestURL)
                    .post(RequestBody.create(JSON, jsonMessage))
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.info("error + Connect Server Error is " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    log.info("Response Body is " + response.body().string());
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
    public void syncGet(String requestURL) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(requestURL)
                    .build();

            Response response = client.newCall(request).execute();

            String message = response.body().string();
            log.info(message);
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
                    log.info("error + Connect Server Error is " + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    log.info("Response Body is " + response.body().string());
                }
            });

        } catch (Exception e){
            System.err.println(e.toString());
        }
    }

    /**
     * 동기방식 :PUT
     * @param requestURL
     */
    public void syncPut(String requestURL, String jsonMessage) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(requestURL)
                    .put(RequestBody.create(JSON, jsonMessage))
                    .build();

            Response response = client.newCall(request).execute();

            String message = response.body().string();
            log.info(message);
        } catch (Exception e){
            System.err.println(e.toString());
        }
    }
}
