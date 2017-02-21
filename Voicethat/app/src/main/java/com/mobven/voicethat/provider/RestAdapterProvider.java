package com.mobven.voicethat.provider;

import com.mobven.voicethat.utils.ApplicationConstant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MobvenCanerYilmaz on 21/02/2017.
 */

public class RestAdapterProvider {

    private static Retrofit sAdapter;

    public static Retrofit getInstance() {
        if (sAdapter == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.header("Content-Type", "application/json");

                    return chain.proceed(requestBuilder.build());
                }
            });
            httpClient.connectTimeout(80, TimeUnit.MINUTES);
            httpClient.readTimeout(80, TimeUnit.MINUTES);
            httpClient.writeTimeout(80, TimeUnit.MINUTES);
            httpClient.addInterceptor(logging);


            sAdapter = new Retrofit.Builder().baseUrl(ApplicationConstant.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

        }
        return sAdapter;
    }




}
