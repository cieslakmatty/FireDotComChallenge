package ie.cieslak.mateusz.weatherapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentCity extends Fragment {
    /**
     * I wanted to add another fragment but it's almost 12 and I still need to pack up
     * for the festival I'm leaving for tomorrow, sorry.
     */

    public FragmentCity() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city, container, false);
        return rootView;
    }
}