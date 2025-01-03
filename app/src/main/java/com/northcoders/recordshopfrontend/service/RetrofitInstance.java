package com.northcoders.recordshopfrontend.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://10.0.2.2:8080/api/v1/";

    public static AlbumApiService getService() {
        if(retrofit == null) {

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient
                            .Builder()
                            .addInterceptor(new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .build();
        }
        AlbumApiService service = retrofit.create(AlbumApiService.class);
        return service;
    }
}
