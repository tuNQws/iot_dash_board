package com.example.iotdashboard.Service.Retrofit;

import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Model.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("device")
    Call<List<Device>> getDevices();

    @GET("temperature")
    Call<List<Log>> getTemperature();

    @GET("humidity")
    Call<List<Log>> getHumidity();

    @GET("light")
    Call<List<Log>> getLight();

    @GET("log")
    Call<List<Log>> getLog();

    @GET("predict")
    Call<String> getPrediction(@Query("input") String input);
}
