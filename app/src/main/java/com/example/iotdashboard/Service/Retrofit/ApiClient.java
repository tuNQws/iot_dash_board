package com.example.iotdashboard.Service.Retrofit;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {
    private static ApiClient apiClient;

    private static final String BASE_URL = "http://192.168.66.54:5000/api/"; //localhost

    private Retrofit retrofit;

    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiClient getInstance(){
        if (apiClient==null){
            apiClient = new ApiClient();
        }
        return apiClient;
    }

    public ApiService getApiService(){
        return retrofit.create(ApiService.class);
    }
}
