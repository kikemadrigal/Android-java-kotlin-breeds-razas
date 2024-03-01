package es.tipolisto.breeds.data.model;



public class Cat {
    private Weight weight;
    private String id;
    private String name;
    private String cfa_url;
    private String vetstreet_url;
    private String vcahospitals_url;
    private String temperament;
    private String origin;
    private String country_codes;
    private String country_code;
    private String description;
    private String life_span;
    private int indoor;
    private int lap;
    private String alt_names;
    private int adaptability;
    private int affection_level;
    private int child_friendly;
    private int cat_friendly;
    private int dog_friendly;
    private int energy_level;
    private int grooming;
    private int health_issues;
    private int intelligence;
    private int shedding_level;
    private int social_needs;
    private int stranger_friendly;
    private int vocalisation;
    private int experimental;
    private int hairless;
    private int natural;
    private int rare;
    private int rex;
    private int suppressed_tail;
    private int short_legs;
    private String wikipedia_url;
    private int hypoallergenic;
    private String reference_image_id;
    private ImageCat image;

    public Weight getWeight() {
        return weight;
    }

    public void setImage(ImageCat image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCfa_url() {
        return cfa_url;
    }

    public String getVetstreet_url() {
        return vetstreet_url;
    }

    public String getVcahospitals_url() {
        return vcahospitals_url;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getCountry_codes() {
        return country_codes;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getDescription() {
        return description;
    }

    public String getLife_span() {
        return life_span;
    }

    public int getIndoor() {
        return indoor;
    }

    public int getLap() {
        return lap;
    }

    public String getAlt_names() {
        return alt_names;
    }

    public int getAdaptability() {
        return adaptability;
    }

    public int getAffection_level() {
        return affection_level;
    }

    public int getChild_friendly() {
        return child_friendly;
    }

    public int getCat_friendly() { return cat_friendly; }

    public int getDog_friendly() {
        return dog_friendly;
    }

    public int getEnergy_level() {
        return energy_level;
    }

    public int getGrooming() {
        return grooming;
    }

    public int getHealth_issues() {
        return health_issues;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getShedding_level() {
        return shedding_level;
    }

    public int getSocial_needs() {
        return social_needs;
    }

    public int getStranger_friendly() {
        return stranger_friendly;
    }

    public int getVocalisation() {
        return vocalisation;
    }

    public int getExperimental() {
        return experimental;
    }

    public int getHairless() {
        return hairless;
    }

    public int getNatural() {
        return natural;
    }

    public int getRare() {
        return rare;
    }

    public int getRex() {
        return rex;
    }

    public int getSuppressed_tail() {
        return suppressed_tail;
    }

    public int getShort_legs() {
        return short_legs;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }

    public int getHypoallergenic() {
        return hypoallergenic;
    }

    public String getReference_image_id() {
        return reference_image_id;
    }

    public ImageCat getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cfa_url='" + cfa_url + '\'' +
                ", vetstreet_url='" + vetstreet_url + '\'' +
                ", vcahospitals_url='" + vcahospitals_url + '\'' +
                ", temperament='" + temperament + '\'' +
                ", origin='" + origin + '\'' +
                ", country_codes='" + country_codes + '\'' +
                ", country_code='" + country_code + '\'' +
                ", description='" + description + '\'' +
                ", life_span='" + life_span + '\'' +
                ", indoor=" + indoor +
                ", lap=" + lap +
                ", alt_names='" + alt_names + '\'' +
                ", adaptability=" + adaptability +
                ", affection_level=" + affection_level +
                ", child_friendly=" + child_friendly +
                ", cat_friendly=" + cat_friendly +
                ", dog_friendly=" + dog_friendly +
                ", energy_level=" + energy_level +
                ", grooming=" + grooming +
                ", health_issues=" + health_issues +
                ", intelligence=" + intelligence +
                ", shedding_level=" + shedding_level +
                ", social_needs=" + social_needs +
                ", stranger_friendly=" + stranger_friendly +
                ", vocalisation=" + vocalisation +
                ", experimental=" + experimental +
                ", hairless=" + hairless +
                ", natural=" + natural +
                ", rare=" + rare +
                ", rex=" + rex +
                ", suppressed_tail=" + suppressed_tail +
                ", short_legs=" + short_legs +
                ", wikipedia_url='" + wikipedia_url + '\'' +
                ", hypoallergenic=" + hypoallergenic +
                ", reference_image_id='" + reference_image_id + '\'' +
                ", image=" + image +
                '}';
    }
}
/*
[
      {
        "weight": {
            "imperial": "7  -  10",
            "metric": "3 - 5"
         },
        "id": "abys",
        "name": "Abyssinian",
        "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
        "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
        "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
        "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
        "origin": "Egypt",
        "country_codes": "EG",
        "country_code": "EG",
        "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        "life_span": "14 - 15",
        "indoor": 0,
        "lap": 1,
        "alt_names": "",
        "adaptability": 5,
        "affection_level": 5,
        "child_friendly": 3,
        "dog_friendly": 4,
        "energy_level": 5,
        "grooming": 1,
        "health_issues": 2,
        "intelligence": 5,
        "shedding_level": 2,
        "social_needs": 5,
        "stranger_friendly": 5,
        "vocalisation": 1,
        "experimental": 0,
        "hairless": 0,
        "natural": 1,
        "rare": 0,
        "rex": 0,
        "suppressed_tail": 0,
        "short_legs": 0,
        "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        "hypoallergenic": 0,
        "reference_image_id": "0XYvRd7oD",
        "image": {
            "id": "0XYvRd7oD",
            "width": 1204,
            "height": 1445,
            "url": "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        }
     },
     {},
     {},
     {},
     {},
     {},
     {}
]

 */