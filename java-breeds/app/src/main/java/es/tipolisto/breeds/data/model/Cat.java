package es.tipolisto.breeds.data.model;


import java.util.List;

public class Cat {
    /*Cuando consultas una raza: //https://api.thecatapi.com/v1/images/search?breed_ids=dons
     te devuelve una array que contiene un solo Cat:
     [
        {
            breads [weighr, is, name, temperament,etc],
            id,
            url,
            width,
            height,
        }
     ]





[
    {
       "breeds":
       [
            "weight": {
                "imperial": "10 - 12",
                "metric": "5 - 6"
            },
            "id": "dons",
            "name": "Donskoy",
            "temperament": "Playful, affectionate, loyal, social",
            "origin": "Russia",
            "country_codes": "RU",
            "country_code": "RU",
            "description": "Donskoy are affectionate, intelligent, and easy-going. They demand lots of attention and interaction. The Donskoy also gets along well with other pets. It is now thought the same gene that causes degrees of hairlessness in the Donskoy also causes alterations in cat personality, making them calmer the less hair they have.",
            "life_span": "12 - 15",
            "indoor": 0,
            "adaptability": 4,
            "affection_level": 4,
            "child_friendly": 3,
            "cat_friendly": 3,
            "dog_friendly": 3,
            "energy_level": 4,
            "grooming": 2,
            "health_issues": 3,
            "intelligence": 3,
            "shedding_level": 1,
            "social_needs": 5,
            "stranger_friendly": 5,
            "vocalisation": 2,
            "experimental": 0,
            "hairless": 1,
            "natural": 0,
            "rare": 1,
            "rex": 0,
            "suppressed_tail": 0,
            "short_legs": 0,
            "wikipedia_url": "https://en.wikipedia.org/wiki/Donskoy_(cat)",
            "hypoallergenic": 0,
            "reference_image_id": "3KG57GfMW"
        ],
        "id": "3KG57GfMW",
        "url": "https://cdn2.thecatapi.com/images/3KG57GfMW.jpg",
        "width": 750,
        "height": 750
    }
]
     */

    private List<Breeds> breeds;
    private String id;
    private String url;
    private String width;
    private String height;

    public Cat(List<Breeds> breeds, String id, String url, String width, String height) {
        this.breeds = breeds;
        this.id = id;
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Breeds> getBreeds() {
        return breeds;
    }

    public String getId() {
        return id;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breeds=" + breeds +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}

