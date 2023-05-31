package com.example.iotdashboard.Service.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotdashboard.R;
import com.example.iotdashboard.Service.Adapter.DeviceAdapter;
import com.example.iotdashboard.Service.Adapter.LogAdapter;
import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Model.Log;
import com.example.iotdashboard.Service.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogsFragment extends Fragment {
    List<Log> logList = new ArrayList<>();
    LogAdapter logAdapter;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.log_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        retrieveJson();
    }

    private void retrieveJson() {
        Call<List<Log>> call = ApiClient.getInstance().getApiService().getLog();

        call.enqueue(new Callback<List<Log>>() {
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                logList.clear();
                logList = response.body();

                logAdapter = new LogAdapter(getContext(), logList);

                recyclerView.setAdapter(logAdapter);
            }

            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
