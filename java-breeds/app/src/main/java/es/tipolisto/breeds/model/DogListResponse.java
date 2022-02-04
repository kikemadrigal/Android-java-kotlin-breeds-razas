package es.tipolisto.breeds.model;

import java.util.List;

public class DogListResponse {
    private List<String> message;
    private String status;

    public List<String> getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
