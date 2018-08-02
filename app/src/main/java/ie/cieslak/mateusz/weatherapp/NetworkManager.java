package ie.cieslak.mateusz.weatherapp;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import java.util.Formatter;

import cz.msebera.android.httpclient.Header;

public class NetworkManager {

    static final String OWM_URL = "https://api.openweathermap.org/data/2.5/weather";
    static final String COORDS = "?lat=%f&lon=%f";
    static final String CITY_NAME = "?q=%s";

    static FragmentGeo fragmentGeo;

    NetworkManager(FragmentGeo fragment) {
        fragmentGeo = fragment;
    }

    public static boolean getWeather(double lan, double lon) {

        String endpoint = OWM_URL + COORDS + "&appid=" + BuildConfig.OWM_TOKEN;

        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format(endpoint, lan, lon);
        requestData(sbuf.toString());
        return true;
    }

    private static void requestData(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("ASDF", "JSON: " + response.toString());

                try {
                    //String price = response.getString("ask");
                    if (fragmentGeo != null)
                        fragmentGeo.updateWeather(response);
                    /*else if (fragmentCity != null){
                        fragmentCity.updateWeather(response);*/
                    //Now we can use the value in the mPriceTextView

                } catch (Exception e) {

                    Log.e("asdf", e.toString());

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e,
                                  JSONObject response) {

                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("ERROR", e.toString());
            }
        });
    }
}
