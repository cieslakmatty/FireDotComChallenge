package ie.cieslak.mateusz.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Formatter;

public class HttpConnection {

    static final String API_KEY = "b9de121e5d0ba841ce8c0c5f78ec09ef";
    static final String OWM_URL = "api.openweathermap.org/data/2.5/weather";
    static final String COORDS = "?lat=%f&lon=%f";
    static final String CITY_NAME = "?q=%s";

    public static boolean getWeather(double lan, double lon) {

        String endpoint = OWM_URL + COORDS;

        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format(endpoint, lan, lon);
        try {
            sendGET(sbuf.toString());
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    private static void sendGET(String url) throws IOException {
        URL obj = new URL(OWM_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }
}
