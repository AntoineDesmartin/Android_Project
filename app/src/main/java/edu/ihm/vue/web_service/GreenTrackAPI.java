package edu.ihm.vue.web_service;

import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.User;
import edu.ihm.vue.models.web_service_models.Credentials;
import edu.ihm.vue.models.web_service_models.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GreenTrackAPI {
    @Headers("Content-Type: application/json")
    @POST("reports")
    Call<Signalement> createReport(@Body Signalement report, @Header("Authorization") String bearer);

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<Token> login(@Body Credentials credentials);
}