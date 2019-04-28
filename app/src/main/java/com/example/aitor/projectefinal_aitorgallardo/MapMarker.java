package com.example.aitor.projectefinal_aitorgallardo;

public class MapMarker {
    private String name;
    private Double lon;
    private Double lat;


    public MapMarker(String name, Double lon, Double lat){
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

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
