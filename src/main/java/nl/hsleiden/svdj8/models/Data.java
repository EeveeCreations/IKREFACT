package nl.hsleiden.svdj8.models;

import java.util.HashMap;

public class Data {
    private String token;
    private char duty;
    private HashMap<String,Object>givenVariables;

//    Constructor

    public Data(String token, char duty, HashMap<String, Object> givenVariables) {
        this.token = token;
        this.duty = duty;
        this.givenVariables = givenVariables;
    }

//    Getters and Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public char getDuty() {
        return duty;
    }

    public void setDuty(char duty) {
        this.duty = duty;
    }

    public HashMap<String, Object> getGivenVariables() {
        return givenVariables;
    }

    public void setGivenVariables(HashMap<String, Object> givenVariables) {
        this.givenVariables = givenVariables;
    }

}
