package com.example.iotdashboard.Service.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotdashboard.R;
import com.example.iotdashboard.Service.Model.Device;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    Context context;

    List<Device> deviceList;

    public DeviceAdapter(Context context, List<Device> deviceList) {
        this.context = context;
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public DeviceAdapter.DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.devices_item, parent, false);
        return new DeviceViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        Picasso.get().load(deviceList.get(position).getImage()).into(holder.imageView);
        holder.deviceName.setText(deviceList.get(position).getName());
        holder.lastConnect.setText(deviceList.get(position).getLastUpdate());
    }

    public int getItemCount() {
        return deviceList.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        TextView deviceName;

        TextView lastConnect;

        public DeviceViewHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.device_image);
            deviceName = itemView.findViewById(R.id.device_name);
            lastConnect = itemView.findViewById(R.id.last_connect);

        }
    }
}
