package com.example.android.Homework2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.Homework2.content.CityUtils;
import com.example.android.Homework2.datamodel.City;
import com.example.android.Homework2.datamodel.Weather;
import com.example.android.Homework2.datamodel.WeatherType;
import com.example.android.Homework2.network.CityResultsObserver;
import com.example.android.Homework2.network.ForecastForACityResultsObserver;
import com.example.android.Homework2.network.IpmaWeatherClient;
import com.example.android.Homework2.network.WeatherTypesResultsObserver;

import java.util.HashMap;
import java.util.List;


public class CityDetailFragment extends Fragment {


    public String mCity;

    private TextView feedback;

    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;

    public CityDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(CityUtils.City_ID_KEY)) {
            // Load the content specified by the fragment arguments.
            mCity = CityUtils.City_ITEMS.get(getArguments()
                    .getInt(CityUtils.City_ID_KEY));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.city_detail, container, false);
        feedback = rootView.findViewById(R.id.city_detail);
        if (mCity != null) {
            callWeatherForecastForACityStep1(mCity);
        }
        return rootView;
    }

    public static CityDetailFragment newInstance (int selectedCity) {
        CityDetailFragment fragment = new CityDetailFragment();
        // Set the bundle arguments for the fragment.
        Bundle arguments = new Bundle();
        arguments.putInt(CityUtils.City_ID_KEY, selectedCity);
        fragment.setArguments(arguments);
        return fragment;
    }

    private void callWeatherForecastForACityStep1(String city) {

        feedback.append("\nGetting forecast for: " + city); feedback.append("\n");

        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                CityDetailFragment.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2( city);
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get weather conditions!");
            }
        });

    }

    private void callWeatherForecastForACityStep2(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                CityDetailFragment.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                if( null != cityFound) {
                    int locationId = cityFound.getGlobalIdLocal();
                    callWeatherForecastForACityStep3(locationId);
                } else {
                    feedback.append("unknown city: " + city);
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                feedback.append("Failed to get cities list!");
            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    feedback.append(day.toString());
                    feedback.append("\t");
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append( "Failed to get forecast for 5 days");
            }
        });

    }
}