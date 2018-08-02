package ie.cieslak.mateusz.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    String name;
    Float tempCurrent;
    Float tempMax;
    Float tempMin;
    int humidity;
    float windSpeed;
    String weatherIconUrl;
    int sunrise;
    int sunset;

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
            sunrise = Integer.parseInt(data.getJSONObject("sys").getString("sunrise"));
            sunset = Integer.parseInt(data.getJSONObject("sys").getString("sunset"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
