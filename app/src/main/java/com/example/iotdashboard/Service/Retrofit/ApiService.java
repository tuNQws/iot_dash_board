package com.example.iotdashboard.Service.Retrofit;

import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Model.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

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
}
