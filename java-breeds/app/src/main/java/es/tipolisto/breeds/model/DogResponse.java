package es.tipolisto.breeds.model;

public class DogResponse {
    //Ruta: https://dog.ceo/api/breeds/image/random
    //JSON:
    /*
    {
        "message": "https://images.dog.ceo/breeds/spaniel-cocker/n02102318_7039.jpg",
        "status": "success"
    }
    */
     private String message;
     private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
