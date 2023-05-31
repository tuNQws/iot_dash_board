package com.example.iotdashboard.Service.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iotdashboard.R;
import com.example.iotdashboard.Service.Adapter.DeviceAdapter;
import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Model.Log;
import com.example.iotdashboard.Service.Retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private ProgressBar tempProgress;
    private ProgressBar humidProgress;
    private ProgressBar lightProgress;

    private TextView tempTextView;
    private TextView humidTextView;
    private TextView lightTextView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tempProgress = view.findViewById(R.id.temperature_progress_bar);
        humidProgress = view.findViewById(R.id.humid_progress_bar);
        lightProgress = view.findViewById(R.id.light_progress_bar);
        tempTextView = view.findViewById(R.id.temp_text_view);
        humidTextView = view.findViewById(R.id.humid_text_view);
        lightTextView = view.findViewById(R.id.light_text_view);

        retrieveJson();
    }

    private void retrieveJson() {
        // Get temperature
        Call<List<Log>> call = ApiClient.getInstance().getApiService().getTemperature();

        call.enqueue(new Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                List<Log> temperature = response.body();
                int value = temperature.get(0).getDeviceValue();
                tempTextView.setText(String.valueOf(value));

                tempProgress.setProgress((value * 100) / 45);
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // Get humidity
        call = ApiClient.getInstance().getApiService().getHumidity();

        call.enqueue(new Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                List<Log> humid = response.body();
                int value = humid.get(0).getDeviceValue();
                humidTextView.setText(String.valueOf(value));

                humidProgress.setProgress(value);
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        call = ApiClient.getInstance().getApiService().getLight();

        call.enqueue(new Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                List<Log> light = response.body();
                int value = light.get(0).getDeviceValue();
                lightTextView.setText(String.valueOf(value));

                value = Math.min(value, 100);
                lightProgress.setProgress(value);
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
