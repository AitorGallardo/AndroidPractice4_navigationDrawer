package com.example.aitor.projectefinal_aitorgallardo;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LugaresBDService bd;
    private Cursor cursor;
    ArrayList<MapMarker> marksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        marksList = new ArrayList<MapMarker>();

        bd = new LugaresBDService(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
         LatLng sydney = new LatLng(-34, 151);
         MarkerOptions test = new MarkerOptions();
         test.position(sydney);
         test.title("Marker in Sydney");
         test.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
         mMap.addMarker(test);

         // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //

       cursor = bd.placesList();

       // PETA AL ACCEDET AL CURSOR ---->>. MIRAR
      if (cursor.moveToFirst()) {
          while (!cursor.isAfterLast()) {

              String name = cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_NOMBRE));
              Double lon = Double.parseDouble(cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_LONGITUD)));
              Double lat = Double.parseDouble(cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_LATITUD)));

              marksList.add(new MapMarker(name, lon, lat));
              cursor.moveToNext();
          }
      }

       for(MapMarker marker : marksList){

           LatLng testlat = new LatLng(marker.getLat(),marker.getLat());
           Log.d("DONDE------>", marker.getLat()+", "+marker.getLon());
           MarkerOptions mark = new MarkerOptions();
           mark.position(testlat);
           mark.title(marker.getName());
           mark.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

//          mMap.addMarker(new MarkerOptions().position(new LatLng(marker.getLat(), marker.getLat()))
//                                           .title(marker.getName())
//                                           .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
           mMap.addMarker(mark);

       }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
