package com.example.iotdashboard.Service.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.iotdashboard.R;
import com.example.iotdashboard.Service.Adapter.DeviceAdapter;
import com.example.iotdashboard.Service.Handler.MqttHandler;
import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Model.Log;
import com.example.iotdashboard.Service.Retrofit.ApiClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private ProgressBar tempProgress;
    private ProgressBar humidProgress;
    private ProgressBar lightProgress;

    private ProgressBar predictHumid;

    private TextView tempTextView;
    private TextView humidTextView;
    private TextView lightTextView;

    private ImageButton ledButton;

    private Button predictButton;

    private TextView predictHumidText;

    private MqttHandler mqttHandler;

    private static final String BROKER_URL = "tcp://broker.emqx.io:1883";
    private boolean isOn;

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
        predictHumid = view.findViewById(R.id.prediction_humid_progress_bar);
        tempTextView = view.findViewById(R.id.temp_text_view);
        humidTextView = view.findViewById(R.id.humid_text_view);
        lightTextView = view.findViewById(R.id.light_text_view);
        ledButton = view.findViewById(R.id.led_button);
        predictButton = view.findViewById(R.id.button);
        predictHumidText = view.findViewById(R.id.humid_text_view2);
        String clientId = MqttClient.generateClientId();
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL,clientId);
        isOn = false;
        ledButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = "CTTT/Group13/led/n2";
                String message;
                if (!isOn) {
                    message = "on";
                    ledButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_shape, getContext().getTheme()));
                } else {
                    message = "off";
                    ledButton.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.circle_shape_disabled, getActivity().getTheme()));
                }
                isOn = !isOn;
                publishMessage(topic, message);
            }
        });

        retrieveJson();

        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPredictionValue(tempTextView.getText().toString());
            }
        });
    }

    private void getPredictionValue(String temp){
        Call<String> call = ApiClient.getInstance().getApiService().getPrediction(temp);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String predictString = response.body().substring(0,4);
                double predictDouble = Double.parseDouble(predictString);
                int predictInt = (int) (predictDouble * 100);
                predictHumidText.setText(String.valueOf(predictInt));
                predictHumid.setProgress(predictInt);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        String topic = "CTTT/Group13/led/n2";
        String message = "off";
        publishMessage(topic, message);
        mqttHandler.disconnect();
        super.onDestroy();
    }

    private void publishMessage(String topic, String message){
        Toast.makeText(getActivity().getBaseContext(), "Publishing message: " + message, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic, message);
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
