package edu.ihm.vue.web_service;

import edu.ihm.vue.models.Signalement;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GreenTrackAPI {
    @Headers("Content-Type: application/json")
    @POST("reports")
    Call<Signalement> createReport(@Body Signalement report, @Header("Authorization") String bearer);
}
