package com.example.iotdashboard.Service.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iotdashboard.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    private LineChart tempChart;
    private LineChart lightChart;
    private BarChart humidChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tempChart = view.findViewById(R.id.temp_line_chart);
        lightChart = view.findViewById(R.id.light_line_chart);
        humidChart = view.findViewById(R.id.humid_bar_chart);



        loadTempChart();
        loadLightChart();
        loadHumidChart();
    }

    private void loadHumidChart() {
        BarDataSet barDataSet = new BarDataSet(humidValue(), "Humidity");
        barDataSet.setColor(Color.BLUE);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);

        XAxis xAxis = humidChart.getXAxis();
        YAxis yAxisLeft = humidChart.getAxisLeft();
        YAxis yAxisRight = humidChart.getAxisRight();

        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisLeft.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);

        humidChart.setData(barData);
        humidChart.invalidate();
    }

    private void loadLightChart() {
        LineDataSet lineDataSet = new LineDataSet(lightValue(), "Light");
        lineDataSet.setLineWidth(4);
        lineDataSet.setColor(Color.parseColor("#FFE4703B"));
        lineDataSet.setCircleColor(Color.parseColor("#FFE4703B"));
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        XAxis xAxis = lightChart.getXAxis();
        YAxis yAxisLeft = lightChart.getAxisLeft();
        YAxis yAxisRight = lightChart.getAxisRight();

        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new MyAxisValueFormatter());
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisLeft.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);
        yAxisRight.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        LineData data = new LineData(dataSets);
        lightChart.setData(data);
        lightChart.invalidate();
    }

    private void loadTempChart() {
        LineDataSet lineDataSet = new LineDataSet(tempValue(), "Temperature");
        lineDataSet.setLineWidth(4);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.RED);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        XAxis xAxis = tempChart.getXAxis();
        YAxis yAxisLeft = tempChart.getAxisLeft();
        YAxis yAxisRight = tempChart.getAxisRight();

        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new MyAxisValueFormatter());
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);
        yAxisLeft.setDrawGridLines(false);
        xAxis.setDrawGridLines(false);
        yAxisRight.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        LineData data = new LineData(dataSets);
        tempChart.setData(data);
        tempChart.invalidate();
    }

    private List<BarEntry> humidValue() {
        List<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(0, 80));
        entries.add(new BarEntry(2, 70));
        entries.add(new BarEntry(4, 90));
        entries.add(new BarEntry(6, 60));
        entries.add(new BarEntry(8, 55));
        entries.add(new BarEntry(10, 92));
        entries.add(new BarEntry(12, 70));

        return entries;
    }

    private List<Entry> lightValue() {
        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 80));
        entries.add(new Entry(1, 75));
        entries.add(new Entry(2, 76));
        entries.add(new Entry(3, 90));
        entries.add(new Entry(4, 100));
        entries.add(new Entry(5, 60));

        return entries;
    }

    private ArrayList<Entry> tempValue() {
        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(0, 50));
        entries.add(new Entry(1, 100));
        entries.add(new Entry(2, 80));
        entries.add(new Entry(3, 200));
        entries.add(new Entry(4, 180));
        entries.add(new Entry(5, 190));

        return entries;
    }

    private class MyAxisValueFormatter extends ValueFormatter {
        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            axis.setLabelCount(4, true);
            String day = "1Y";
            if (value <= 0){
                day = "D";
            } else if (value <= 1){
                day = "1W";
            } else if (value <= 2){
                day = "1M";
            } else if (value <= 4){
                day = "6M";
            }
            return day;
        }
    }
}
