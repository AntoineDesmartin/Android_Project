package edu.ihm.vue.web_service;

import edu.ihm.vue.models.Signalement;
import edu.ihm.vue.models.User;
import edu.ihm.vue.models.web_service_models.Credentials;
import edu.ihm.vue.models.web_service_models.Token;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface GreenTrackAPI {
    @Headers("Content-Type: application/json")
    @POST("reports")
    Call<Signalement> createReport(@Body Signalement report);

    @Multipart
    @POST("images")
    Call<Void> addImageToReport(@Part MultipartBody.Part image, @Part("reportId") int reportId);

    @GET("me")
    Call<User> me();

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<Token> login(@Body Credentials credentials);
}