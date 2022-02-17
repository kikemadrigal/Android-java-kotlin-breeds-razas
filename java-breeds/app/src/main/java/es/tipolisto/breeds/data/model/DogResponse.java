package es.tipolisto.breeds.data.model;

import java.util.List;

public class DogResponse {
    private List<BreedsDog> breeds;
    private String id;
    private String url;
    private int width;
    private int height;


    public List<BreedsDog> getBreeds() {
        return breeds;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "DogResponse{" +
                "breeds=" + breeds +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
