package edu.ihm.vue.models.web_service_models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Credentials {
    private final String username;
    private final String password;

    @JsonCreator
    public Credentials(@JsonProperty("username") String type, @JsonProperty("password") String password) {
        this.username = type;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}