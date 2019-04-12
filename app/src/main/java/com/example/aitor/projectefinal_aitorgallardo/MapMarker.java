package com.example.aitor.projectefinal_aitorgallardo;

public class MapMarker {
    private String name;
    private int lon;
    private int lat;


    public MapMarker(String name, int lon, int lat){
        this.name = name;
        this.lon = lon;
        this.lat = lat;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }
}
