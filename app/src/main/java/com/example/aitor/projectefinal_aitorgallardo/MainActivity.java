package com.example.aitor.projectefinal_aitorgallardo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EditFragment.OnFragmentInteractionListener,PlacesListFragment.OnFragmentInteractionListener {

    Intent mapIntent;

    private LugaresBDService bd;
    Cursor cursorLatLon;

    WeatherService weatherService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Projecte Final");
        mapIntent = new Intent(this,MapsActivity.class);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // We gets the weather once a time the application is launched (NOT WORKING, AsyncHttpResponseHandler() doesnt trigger any of the default functions)

    //            bd = new LugaresBDService(this);
    //            cursorLatLon = bd.getLatLon();
    //
    //            weatherService = new WeatherService();
    //            if (cursorLatLon.moveToFirst()) {
    //                while (!cursorLatLon.isAfterLast()) {
    //                    long id = cursorLatLon.getLong(cursorLatLon.getColumnIndex(bd.PLACESLIST_ID));
    //                    String lat = cursorLatLon.getString(cursorLatLon.getColumnIndex(bd.PLACESLIST_LATITUD));
    //                    String lon = cursorLatLon.getString(cursorLatLon.getColumnIndex(bd.PLACESLIST_LONGITUD));
    //                     String icon = weatherService.getWeather(lat,lon).getIcon();
    //
    //                   // bd.updateWeather(id, "hh");
    //                    cursorLatLon.moveToNext();
    //                }
    //            }



        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.your_placeholder, new PlacesListFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add) {
            try{
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.your_placeholder, new EditFragment()).commit();
            }catch(Exception e){
                Log.d("ERROR----: ", e.toString());
            }finally{
                Log.d("ERROR----: ","FRAGMENT ENTRA ENTRA");
            }

        } else if (id == R.id.nav_list) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.your_placeholder, new PlacesListFragment()).commit();

        } else if (id == R.id.nav_map) {
            startActivity(mapIntent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
