package com.dia.app.support.net;

import com.dia.common.constants.ErrorMessage;
import com.dia.common.constants.ResponseCode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class OkHttpManager {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpManager.class);

    private OkHttpClient okHttpClient;
    private Gson gson = new Gson();

    public static OkHttpManager getInstance() {
        return new OkHttpManager();
    }

    private OkHttpManager() {
        okHttpClient = buildClient();
    }

    private OkHttpClient buildClient(){
        return new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.WRITE_TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public String postRequest(String url, String json){
        return sendRequest(url, json, "POST");
    }

    private String sendRequest(String url, String json, String method){
        RequestBody requestBody = RequestBody.create(HttpConfig.MEDIA_TYPE_JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .method(method, requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                return "";
            }
            return responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <T> T post(String url, String json, Type type) {
        String resp = sendRequest(url, json, "POST");
        if (StringUtils.isEmpty(resp)) {
            JsonObject jErp = new JsonObject();
            jErp.addProperty("code", ResponseCode.INTERNAL_ERROR);
            jErp.addProperty("msg", ErrorMessage.map.get(ResponseCode.INTERNAL_ERROR));
            resp = jErp.toString();
        }
        logger.info("post resp = {}", resp);
        return gson.fromJson(resp, type);
    }
}
