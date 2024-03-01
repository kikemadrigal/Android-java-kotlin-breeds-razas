package es.tipolisto.breeds.data.model;

public class ImageCat {
    private String id;
    private String url;
    private int width;
    private int height;


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
        return "Image{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
