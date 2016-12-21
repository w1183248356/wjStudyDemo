package com.network.network.interceptor;

import android.util.Log;

import com.network.BuildConfig;
import com.network.network.util.L;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author wangjian
 * @title RequestInterceptor
 * @description
 * @modifier
 * @date
 * @since 2016/12/7 9:59
 **/
public class RequestInterceptor implements Interceptor {
    private static final String TAG = "RxResponTransform";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Response response;
        try {
            response = chain.proceed(creatNewRequest(original));
        } catch (SocketTimeoutException e) {
            return createErrorRespon(original);
        }
        if (BuildConfig.DEBUG)
            L.t(TAG).i(String.format("\nresponse_headers====>%s\n%s", response, response.headers()));

        return createNewRespon(original, response.body().string());
    }


    private Response createNewRespon(Request request, String body) {
        FormBody formBody = (FormBody) request.body();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        JSONObject oldJsonObject;
        try {
            oldJsonObject = new JSONObject(body);
            JSONObject result = new JSONObject(oldJsonObject.getString("result"));
            for (int i = 0; i < formBody.size(); i++) {
                if (formBody.name(i).equals("oper")) {
                    result.put("action", formBody.value(i));
                }
                if (formBody.name(i).equals("type")) {
                    result.put("controller", formBody.value(i));
                }
            }
            JSONObject newJsonObject = new JSONObject();
            newJsonObject.put("result", result);
            ResponseBody responseBody = ResponseBody.create(mediaType, newJsonObject.toString());
            return new Response.Builder()
                    .code(200)
                    .message("OK")
                    .protocol(Protocol.HTTP_1_1)
                    .request(request)
                    .body(responseBody)
                    .build();
        } catch (JSONException e) {
            JSONObject error = new JSONObject();
            try {
                error.put("code", "409");
                error.put("msg", "返回json格式错误");
                error.put("content", body);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            ResponseBody responseBody = ResponseBody.create(mediaType, error.toString());
            return new Response.Builder()
                    .code(409)
                    .message("返回json格式错误")
                    .protocol(Protocol.HTTP_1_1)
                    .request(request)
                    .body(responseBody)
                    .build();
        }

    }

    private Response createErrorRespon(Request request) {
        FormBody formBody = (FormBody) request.body();
        String action = "";
        String controller = "";
        for (int i = 0; i < formBody.size(); i++) {
            if (formBody.name(i).equals("oper")) {
                action = formBody.value(i);
            }
            if (formBody.name(i).equals("type")) {
                controller = formBody.value(i);
            }
        }
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        JSONObject error = new JSONObject();
        try {
            error.put("code", "408");
            error.put("msg", "网络请求超时" + "action==>" + action + "  controller==>" + controller);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        ResponseBody responseBody = ResponseBody.create(mediaType, error.toString());
        return new Response.Builder()
                .code(408)
                .message("网络请求超时" + "action==>" + action + "  controller==>" + controller)
                .protocol(Protocol.HTTP_1_1)
                .request(request)
                .body(responseBody)
                .build();


    }

    private Request creatNewRequestBak(Request oldRequest) throws UnsupportedEncodingException {
        //请求体定制：统一添加source参数
        Request.Builder requestBuilder = oldRequest.newBuilder();
        //requestBuilder.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        if (oldRequest.body() instanceof FormBody) {
            FormBody.Builder newFormBodyBuilder = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) oldRequest.body();
            Map<String, String> params = new HashMap<>();
            params.put("sign", "");
            params.put("secret", "");
            params.put("source", "B");
            params.put("url", BuildConfig.URL_PARA);
            for (int i = 0; i < oldFormBody.size(); i++) {
                params.put(oldFormBody.name(i), oldFormBody.value(i));
                //newFormBodyBuilder.addEncoded(oldFormBody.name(i), oldFormBody.value(i));
            }
            /*newFormBodyBuilder.addEncoded("sign", "");
            newFormBodyBuilder.addEncoded("secret", "");
            newFormBodyBuilder.addEncoded("source", "B");
            newFormBodyBuilder.addEncoded("url", BuildConfig.URL_PARA);*/
            if (BuildConfig.DEBUG) {
                L.t(TAG).i(TAG, "-------------------------post请求参数Start--------------");

                for (Map.Entry<String, String> entry : params.entrySet()) {
                    L.t(TAG).i(TAG, "name=====>" + entry.getKey() + "   value====>" + entry.getValue());
                }

                L.t(TAG).i(TAG, "-------------------------post请求参数End--------------");
            }

            for (Map.Entry<String, String> entry : params.entrySet()) {
                newFormBodyBuilder.addEncoded(URLEncoder.encode(entry.getKey(), "utf-8"), URLEncoder.encode(entry.getValue(), "utf-8"));
            }

            FormBody newFormBody = newFormBodyBuilder.build();


            requestBuilder.method(oldRequest.method(), newFormBody);
        }
        Request newRequest = requestBuilder.build();

        if (BuildConfig.DEBUG)
            Log.i(TAG, String.format("request===>%s\n%s", newRequest, newRequest.headers()));

        return newRequest;
    }

    private Request creatNewRequest(Request oldRequest) throws UnsupportedEncodingException {
        FormBody oldFormBody = (FormBody) oldRequest.body();
        Map<String, String> params = new HashMap<>();
        /**当前应用版本号**/
        params.put("version", "" + BuildConfig.version);
        params.put("sign", "");
        params.put("secret", "");
        /** 网页(W)、外勤360-android(O)、POS(P)、WAP(A)、B2CAPP-android(C) 、B2BAPP-android(B)、B2BAPP-IOS(F)、B2CAPP-IOS(G)、外勤360-IOS(H)、移动POS-android(I) */
        params.put("source", BuildConfig.source);
        params.put("url", BuildConfig.URL_PARA);
        for (int i = 0; i < oldFormBody.size(); i++) {
            params.put(oldFormBody.name(i), oldFormBody.value(i));
        }
        //日志输出
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "-------------------------post请求参数Start--------------");

            for (Map.Entry<String, String> entry : params.entrySet()) {
                Log.i(TAG, "name=====>" + entry.getKey() + "   value====>" + entry.getValue());
            }

            Log.i(TAG, "-------------------------post请求参数End--------------");
        }

        String content = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            content += entry.getKey() + "=" + entry.getValue() + "&";
        }
        content = content.substring(0, content.length() - 1);
        if (BuildConfig.DEBUG)
            Log.i(TAG, "构造的post参数====》" + content);
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, content);
        Request newRequest = new Request.Builder()
                .url(oldRequest.url().toString())
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        if (BuildConfig.DEBUG)
            L.t(TAG).i(String.format("\nrequest===>%s\n%s", newRequest, newRequest.headers()));

        return newRequest;
    }


}
