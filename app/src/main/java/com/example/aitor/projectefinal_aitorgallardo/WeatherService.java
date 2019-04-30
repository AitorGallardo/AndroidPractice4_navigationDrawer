package com.example.aitor.projectefinal_aitorgallardo;



import android.app.Dialog;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class WeatherService {

    Weather weather;
    private static final String APIKEY = "cac60219a0e9c813b2cbce4b0f34d1d5";

    // REQUEST EXAMPLE = api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon} // api.openweathermap.org/data/2.5/forecast?id=524901&APPID=1111111111

    public Weather getWeather(String lat, String lon) {


        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(0,10000);
        client.addHeader("Authorization","");
        String Url ="api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&APPID="+APIKEY;
        client.get(Url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
//                Dialog.setMessage("Descargando platos...");
//                Dialog.show();
                // snackbar i spinner para mostrar que se esta cargando informacion
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                JSONObject responseObject = null;
                String str = new String(responseBody);


                try {
                    responseObject = new JSONObject(str);
                    JSONArray weatherArray = responseObject.getJSONArray("weather");

                    // Its posible to get more than one weather for the same zone, we are just keeping one of them
                    JSONObject weatherObject = weatherArray.getJSONObject(0);

                    weather = Weather.parseObject(weatherObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                    weather = null;
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                weather = null;
            }
        });

        return weather;
    }
}

class Weather {

    private String description;
    private String icon;

    public Weather(String description, String icon){
        this.description = description;
        this.icon = icon;
    }

    public static Weather parseObject(JSONObject json){
        String description;
        String icon;
        try{

             description = json.getString("description") != null ? json.getString("description"): "No description available";
             icon = json.getString("summary") != null ? "http://openweathermap.org/img/w/"+json.getString("icon")+".png" : null;
            return new Weather(description, icon);

        }catch (JSONException e){
             description ="No description available";
             icon = null;
            e.printStackTrace();
            return new Weather(description, icon);

        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
