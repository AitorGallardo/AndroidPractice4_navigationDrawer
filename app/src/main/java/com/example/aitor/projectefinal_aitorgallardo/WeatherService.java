package com.example.aitor.projectefinal_aitorgallardo;



import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class WeatherService {

    private static final String APIKEY = "cac60219a0e9c813b2cbce4b0f34d1d5";

    // REQUEST EXAMPLE = api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon} // api.openweathermap.org/data/2.5/forecast?id=524901&APPID=1111111111

//    private void actualizarPlatos() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.setMaxRetriesAndTimeout(0,10000);
//
//        client.addHeader("Authorization",pickingDataSource.API_AUTORITZATION);
//
//        String Url = db.getApiBase() + "platos/" + db.getConfiguracion(db.cCONFIGURACION_VALUE_EMPRESA_CODE);
//        client.get(this,Url, new AsyncHttpResponseHandler() {
//
//            @Override
//            public void onStart() {
//                // called before request is started
//                Dialog.setMessage("Descargando platos...");
//                Dialog.show();
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                //public void onSuccess(String content) {
//                Dialog.setMessage("Procesando platos...");
//
//                JSONObject platos = null;
//                String str = new String(responseBody);
//
//                try {
//                    platos = new JSONObject(str);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                Dialog.hide();
//
//                int iPlatos=0;
//
//                try {
//                    iPlatos = db.procesarPlatos(platos);
//                } catch (JSONException e) {
//                    icDialogos.showToastLargo(getApplicationContext(),"Se ha producido un error al procesar las mesas. " + e.getMessage());
//                    return;
//                }
//
//                if (iPlatos >= 0) {
//                    icDialogos.showSnackBarLargo(findViewById(android.R.id.content), "Se han procesado " + String.valueOf(iPlatos) + " platos.");
//                }
//            }

}
