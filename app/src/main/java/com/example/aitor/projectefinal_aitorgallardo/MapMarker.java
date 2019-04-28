package com.example.aitor.projectefinal_aitorgallardo;

public class MapMarker {
    private String name;
    private Double lon;
    private Double lat;
    private String image;


    public MapMarker(String name, Double lon, Double lat, String image){
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String lat) {
        this.image = lat;
    }
}
