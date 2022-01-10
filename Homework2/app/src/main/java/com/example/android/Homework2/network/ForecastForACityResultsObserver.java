package com.example.android.Homework2.network;

import com.example.android.Homework2.datamodel.Weather;

import java.util.List;



public interface ForecastForACityResultsObserver {
    public void receiveForecastList(List<Weather> forecast);
    public void onFailure(Throwable cause);
}
