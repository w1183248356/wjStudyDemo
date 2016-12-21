package com.network.network;


import android.os.Build;

import com.network.BuildConfig;
import com.network.network.interceptor.RequestInterceptor;
import com.network.network.util.L;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * Created by Administrator on 2015/8/21.
 */
public class RetrofitHelp {
    private static final String BASE_URL = BuildConfig.HOSTNAME;
    private static RESTApi api;

    public static RESTApi getApi() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> {
                if (BuildConfig.DEBUG)
                    L.ii(message);
            });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置缓存信息的处理：创建缓存对象，构造方法用于控制缓存位置及最大缓存大小【单位是Byte】
            //Cache cache = new Cache(new File("/mnt/sdcard/wlw"), 10 * 1024 * 1024);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain ->
                            chain.proceed(chain.request().newBuilder()
                                    .header("Accept-Language", buildAcceptLanguage())
                                    .header("User-Agent", buildUserAgent())
                                    .build()))
                    .addInterceptor(logging)
                    .addInterceptor(new RequestInterceptor())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //.addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            api = retrofit.create(RESTApi.class);
        }

        return api;
    }

    private static String buildAcceptLanguage() {
        Locale locale = Locale.getDefault();
        return String.format("%s-%s,%s;q=0.8,en-US;q=0.6,en;q=0.4",
                locale.getLanguage(), locale.getCountry(), locale.getLanguage());
    }

    private static String buildUserAgent() {
        return String.format("WlwB2b %s Android (%d/%s;)",
                BuildConfig.VERSION_NAME,
                Build.VERSION.SDK_INT, Build.VERSION.RELEASE);
    }
}
