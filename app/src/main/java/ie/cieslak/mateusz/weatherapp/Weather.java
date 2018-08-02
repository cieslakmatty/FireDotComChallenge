package ie.cieslak.mateusz.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
    String name;
    Float tempCurrent;
    Float tempMax;
    Float tempMin;
    int humidity;
    float windSpeed;
    String weatherIconUrl;
    String sunrise;
    String sunset;

    static final float KELVIN_TO_CALSIUS = -272.15f;

    Weather(JSONObject data) {
        try {
            name = data.getString("name");
            tempCurrent = Float.parseFloat(data.getJSONObject("main").getString("temp")) + KELVIN_TO_CALSIUS;
            tempMax = Float.parseFloat(data.getJSONObject("main").getString("temp_max")) + KELVIN_TO_CALSIUS;
            tempMin = Float.parseFloat(data.getJSONObject("main").getString("temp_min")) + KELVIN_TO_CALSIUS;
            humidity = Integer.parseInt(data.getJSONObject("main").getString("humidity"));
            windSpeed = Float.parseFloat(data.getJSONObject("wind").getString("speed"));
            weatherIconUrl = "http://openweathermap.org/img/w/" + data.getJSONObject("weather").getString("icon");
            int sunriseUnix = Integer.parseInt(data.getJSONObject("sys").getString("sunrise"));
            Date date = new java.util.Date(sunriseUnix*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
            sunrise = sdf.format(date);

            int sunsetUnix = Integer.parseInt(data.getJSONObject("sys").getString("sunset"));
            date = new java.util.Date(sunsetUnix*1000L);
            sunset = sdf.format(date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
