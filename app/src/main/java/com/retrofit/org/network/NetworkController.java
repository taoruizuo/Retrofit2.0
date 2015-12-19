package com.retrofit.org.network;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Retrofit;

/**
 * Created by hp on 2015/12/19.
 */
public class NetworkController {

    private static final String URL_BASE = "https://api.github.com/";
    private static Retrofit retrofit;

    public static <T> T get(Class<T> clazz) {
        return retrofit.create(clazz);
    }




    static {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                RequestBody requestBody = request.body();
                if (requestBody != null) {
                    Log.i(NetworkController.class.getName(), requestBody.getClass().getName());
                }
                Log.e(NetworkController.class.getName(), String.format("Url:%s", request.urlString()));
                Response response = chain.proceed(request);
                Log.e(NetworkController.class.getName(), response.body().toString());
                return response;
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
//              .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


}
