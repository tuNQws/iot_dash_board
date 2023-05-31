package com.example.iotdashboard.Service.Activity;

import android.os.Bundle;
import android.util.Log;
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
import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragment extends Fragment {

    List<Device> deviceList = new ArrayList<>();
    DeviceAdapter deviceAdapter;

    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_device);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));


        retrieveJson();
    }

    private void retrieveJson() {
        Call<List<Device>> call = ApiClient.getInstance().getApiService().getDevices();
        call.enqueue(new Callback<List<Device>>() {
            @Override
            public void onResponse(Call<List<Device>> call, Response<List<Device>> response) {
                deviceList.clear();
                deviceList = response.body();

                deviceAdapter = new DeviceAdapter(getContext(), deviceList);

                recyclerView.setAdapter(deviceAdapter);
            }

            @Override
            public void onFailure(Call<List<Device>> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }


}
