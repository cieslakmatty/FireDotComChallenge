package ie.cieslak.mateusz.weatherapp;


import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    private FragmentGeo fragmentGeo = new FragmentGeo();
    private FragmentCity fragmentCity = new FragmentCity();

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        switch (position) {
            case 0: return fragmentGeo;
            case 1: return fragmentCity;
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}