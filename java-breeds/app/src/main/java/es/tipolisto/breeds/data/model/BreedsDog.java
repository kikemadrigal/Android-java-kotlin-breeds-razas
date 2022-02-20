package es.tipolisto.breeds.data.model;

public class BreedsDog {
    private Weight weight;
    private Height height;
    private int id;
    private String name;
    private String country_code;
    private String bred_for;
    private String breed_group;
    private String life_span;
    private String temperament;
    private String reference_image_id;

    public Weight getWeight() {
        return weight;
    }

    public Height getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getBred_for() {
        return bred_for;
    }

    public String getBreed_group() {
        return breed_group;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getReference_image_id() {
        return reference_image_id;
    }

    @Override
    public String toString() {
        return "BreedsDog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
