package ie.cieslak.mateusz.weatherapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class FragmentGeo extends Fragment {

    LocationManager locationManager;
    Location currentLocation;
    NetworkManager networkManager;

    public FragmentGeo() {
        networkManager = new NetworkManager(this);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (grantResults[0] == PERMISSION_GRANTED) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, createLocationListener());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(getActivity(), permissions, 0);
        } else {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            assert locationManager != null;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, createLocationListener());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, createLocationListener());
        }
        View view = inflater.inflate(R.layout.fragment_geo, container, false);

        Button updateBtn = view.findViewById(R.id.btn_get_update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Trying to get an update from OWM", Toast.LENGTH_LONG).show();
                NetworkManager.getWeather(currentLocation.getLatitude(),currentLocation.getLongitude());
            }
        });

        return view;
    }

    public void updateWeather(JSONObject data) {
        Weather weather = new Weather(data);
    }

    private void updateLocation(Location location) {

        currentLocation = location;
        TextView textViewLan = (TextView) getView().findViewById(R.id.label_geo_lat);
        textViewLan.setText(String.valueOf(location.getLatitude()));
        TextView textViewLon = (TextView) getView().findViewById(R.id.label_geo_lon);
        textViewLon.setText(String.valueOf(location.getLongitude()));
    }

    private LocationListener createLocationListener() {
       return new LocationListener() {

            public void onLocationChanged(Location location) {

                Button btn = (Button) getView().findViewById(R.id.btn_get_update);
                btn.setEnabled(true);
                updateLocation(location);
                currentLocation = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };
    }
}
