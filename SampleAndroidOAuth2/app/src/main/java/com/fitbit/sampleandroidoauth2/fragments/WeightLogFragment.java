package com.fitbit.sampleandroidoauth2.fragments;


import com.fitbit.api.loaders.ResourceLoaderResult;
import com.fitbit.api.models.Weight;
import com.fitbit.api.models.WeightLogs;
import com.fitbit.api.services.WeightService;
import com.fitbit.sampleandroidoauth2.R;

import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.List;

/**
 * Created by jboggess on 10/17/16.
 */

public class WeightLogFragment extends InfoFragment<WeightLogs> {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        binding.webview.setVisibility(View.GONE);
        binding.graph.setVisibility(View.VISIBLE);

        return v;
    }

    @Override
    public int getTitleResourceId() {
        return R.string.weight;
    }

    @Override
    protected int getLoaderId() {
        return 4;
    }

    @Override
    public Loader<ResourceLoaderResult<WeightLogs>> onCreateLoader(int id, Bundle args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return WeightService.getWeightLogLoader(getActivity(), calendar.getTime(), Calendar.MONTH, 1);
    }

    @Override
    public void onLoadFinished(Loader<ResourceLoaderResult<WeightLogs>> loader, ResourceLoaderResult<WeightLogs> data) {
        super.onLoadFinished(loader, data);
        if (data.isSuccessful()) {
            bindWeightLogs(data.getResult());
        }
    }

    public void bindWeightLogs(WeightLogs weightLogs) {
        List<Weight> weights = weightLogs.getWeight();
        DataPoint[] dataPoints = new DataPoint[weights.size()];

        for (int i = 0; i < weights.size(); i++) {
            Weight weight = weights.get(i);
            dataPoints[i] = new DataPoint(weight.getDateTime(), weight.getWeight());
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        binding.graph.addSeries(series);

        binding.graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
        binding.graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        binding.graph.getViewport().setMinX(dataPoints[0].getX());
        binding.graph.getViewport().setMaxX(dataPoints[dataPoints.length - 1].getX());
        binding.graph.getViewport().setXAxisBoundsManual(true);

        binding.graph.getGridLabelRenderer().setHumanRounding(false);

    }
}
