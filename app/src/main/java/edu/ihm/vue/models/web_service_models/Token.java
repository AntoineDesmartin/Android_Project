package edu.ihm.vue.models.web_service_models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    private final String type;
    private final String token;

    @JsonCreator
    public Token(@JsonProperty("type") String type, @JsonProperty("token") String token) {
        this.type = type;
        this.token = token;
    }

    public String getType() {
        return this.type;
    }

    public String getToken() {
        return "Bearer " + this.token;
    }
}