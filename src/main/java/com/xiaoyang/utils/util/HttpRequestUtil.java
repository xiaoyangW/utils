package com.xiaoyang.utils.util;

import okhttp3.*;
import java.io.IOException;
import java.util.Map;

/**
 * ok http 请求封装
 *
 * @author xiaoyang.wen
 */
public class HttpRequestUtil {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * post 提交json数据
     *
     * @param url   请求url
     * @param json  json data
     * @param token token
     * @return response body
     * @throws IOException io
     */
    public static String postJson(String url, String json, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (json != null && !"".equals(json)) {
            RequestBody body = RequestBody.create(JSON, json);
            builder.post(body);
        }
        if (token != null && !"".equals(token)) {
            builder.addHeader("token", token);
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return response.body().string();
        }
    }

    public static String postJson(String url, String json) throws IOException {
        return postJson(url, json, null);
    }

    /**
     * post 提交form表单
     *
     * @param url   请求url
     * @param map   参数
     * @param token token
     * @return json string
     * @throws IOException io
     */
    public static String postForm(String url, Map<String, String> map, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (map != null) {
            FormBody.Builder body = new FormBody.Builder();
            map.forEach(body::add);
            builder.post(body.build());
        }
        if (token != null && !"".equals(token)) {
            builder.addHeader("token", token);
        }
        Request request = builder.build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            //log.error("ShortUrl postForm request url:{} error :{}", url, response.toString());
            return response.body().string();
        }
    }

    /**
     * post 提交form表单
     *
     * @param url url
     * @param map map data
     * @return json String
     * @throws IOException http io
     */
    public static String postForm(String url, Map<String, String> map) throws IOException {
        return postForm(url, map, null);
    }

    /**
     * 获取下载文件
     *
     * @param url url
     * @return byte[]
     * @throws IOException http io
     */
    public static byte[] getFile(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        if (url != null && !"".equals(url)) {
            Request request = builder.url(url).build();
            Response response = client.newCall(request).execute();
            return response.body().bytes();
        }
        return null;
    }


}
