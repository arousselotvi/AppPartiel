package com.example.antoinerousselot.apppartiel;

public class Destination {
    private String type;
    private String title;
    private Double distance;
    private String imageURL;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Destination(String type, String title, Double distance, String imageURL) {
        this.type = type;
        this.title = title;
        this.distance = distance;
        this.imageURL = imageURL;
    }
}
