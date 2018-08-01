package ie.cieslak.mateusz.weatherapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class FragmentGeo extends Fragment {

    LocationManager locationManager;
    Location currentLocation;

    public FragmentGeo() {}

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (grantResults[0] == PERMISSION_GRANTED) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, createLocationListener());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = { Manifest.permission.ACCESS_COARSE_LOCATION };
            ActivityCompat.requestPermissions(getActivity(), permissions, 0);
        }else {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, createLocationListener());
        }
        return inflater.inflate(R.layout.fragment_geo, container, false);
    }

    private void updateLocation(Location location) {

        TextView textViewLan = (TextView) getView().findViewById(R.id.label_geo_lat);
        textViewLan.setText(String.valueOf(location.getLatitude()));
        TextView textViewLon = (TextView) getView().findViewById(R.id.label_geo_lon);
        textViewLon.setText(String.valueOf(location.getLongitude()));
    }

    private LocationListener createLocationListener() {
       return new LocationListener() {

            public void onLocationChanged(Location location) {

                updateLocation(location);
                currentLocation = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };
    }
}