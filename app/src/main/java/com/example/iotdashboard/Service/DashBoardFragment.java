package com.example.iotdashboard.Service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotdashboard.R;
import com.example.iotdashboard.Service.Adapter.DeviceAdapter;
import com.example.iotdashboard.Service.Model.Device;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dash_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Device> deviceList = new ArrayList<>();

        Device device = new Device("Wemos", "today",
                "https://res.cloudinary.com/dxivl2lh5/image/upload/v1684223884/wemos_q9pwn7.jpg");
        deviceList.add(device);

        device = new Device("Raspery Pi", "today",
                "https://res.cloudinary.com/dxivl2lh5/image/upload/v1684224110/raspery_eytslz.jpg");
        deviceList.add(device);

        DeviceAdapter deviceAdapter = new DeviceAdapter(this.getContext(), deviceList);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_device);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(deviceAdapter);
    }
}
