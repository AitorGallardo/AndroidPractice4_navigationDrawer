package com.example.aitor.projectefinal_aitorgallardo;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
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


    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId) {

        View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        ImageView markerImageView = (ImageView) customMarkerView.findViewById(R.id.profile_image);
            markerImageView.setImageResource(resId);
            customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
            customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
            canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
            if (drawable != null)
                drawable.draw(canvas);
            customMarkerView.draw(canvas);
            return returnedBitmap;
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

        // Position to set main focus
         LatLng barcelona = new LatLng(41.385063, 2.173404);
         mMap.moveCamera(CameraUpdateFactory.newLatLng(barcelona));

        cursor = bd.placesList();

       // PETA AL ACCEDET AL CURSOR ---->>. MIRAR
      if (cursor.moveToFirst()) {
          while (!cursor.isAfterLast()) {

              String name = cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_NOMBRE));
              Double lon = cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_LONGITUD)) != null ? Double.parseDouble(cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_LONGITUD))) : null;
              Double lat = cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_LATITUD))!= null? Double.parseDouble(cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_LATITUD))) : null;
              String image = cursor.getString(cursor.getColumnIndex(bd.PLACESLIST_FOTO));

              if(lat != null && lon != null){
                  marksList.add(new MapMarker(name, lon, lat, image));
              }

              cursor.moveToNext();
          }
      }

       for(MapMarker marker : marksList){

          mMap.addMarker(new MarkerOptions().position(new LatLng(marker.getLat(), marker.getLon()))
                                           .title(marker.getName())
                                            .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(this.getResources().getIdentifier(marker.getImage() , "drawable", this.getPackageName())))));

       }

    }
}
