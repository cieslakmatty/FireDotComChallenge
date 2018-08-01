package ie.cieslak.mateusz.weatherapp;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentGeo  extends Fragment {

    LocationManager locationManager;

    public FragmentGeo() {}
    public FragmentGeo(LocationManager lm){
        locationManager = lm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_geo, container, false);
        return rootView;
    }
}