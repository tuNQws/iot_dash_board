package com.example.iotdashboard.Service.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iotdashboard.R;
import com.example.iotdashboard.Service.Model.Device;
import com.example.iotdashboard.Service.Model.Log;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder>{
    Context context;
    List<Log> logList;

    public LogAdapter(Context context, List<Log> logList) {
        this.context = context;
        this.logList = logList;
    }

    @NonNull
    @Override
    public LogAdapter.LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.log_item, parent, false);
        return new LogAdapter.LogViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull LogAdapter.LogViewHolder holder, int position) {
        holder.logId.setText("ID: " +logList.get(position).getId());
        holder.deviceName.setText(logList.get(position).getDeviceName());
        holder.logIp.setText(logList.get(position).getLogIp());
        holder.sensorType.setText(logList.get(position).getSensorType());
        holder.deviceValue.setText(String.valueOf(logList.get(position).getDeviceValue()) + logList.get(position).getUnit());
        holder.logDateTime.setText(logList.get(position).getLogTime() + " " + logList.get(position).getLogDay());
    }

    public int getItemCount() {
        return logList.size();
    }

    public class LogViewHolder extends RecyclerView.ViewHolder {
        TextView deviceName;

        TextView deviceValue;

        TextView sensorType;

        TextView logDateTime;
        TextView logIp;
        TextView logId;

        public LogViewHolder(@NonNull View itemView){
            super(itemView);

            logId = itemView.findViewById(R.id.log_id);
            logIp = itemView.findViewById(R.id.log_ip);
            logDateTime = itemView.findViewById(R.id.log_datetime);
            sensorType = itemView.findViewById(R.id.sensor_type);
            deviceValue = itemView.findViewById(R.id.device_value);
            deviceName = itemView.findViewById(R.id.log_device_name);

        }
    }

}
