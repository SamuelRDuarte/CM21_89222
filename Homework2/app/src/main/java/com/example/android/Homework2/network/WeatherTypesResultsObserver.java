package com.example.android.Homework2.network;

import com.example.android.Homework2.datamodel.WeatherType;

import java.util.HashMap;



public interface WeatherTypesResultsObserver {
    public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection);
    public void onFailure(Throwable cause);
}
