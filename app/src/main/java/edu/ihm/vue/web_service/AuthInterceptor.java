package edu.ihm.vue.web_service;

import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private final SharedPreferences preferences;
    public AuthInterceptor(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder currentRequest = chain.request().newBuilder();
        String token = this.preferences.getString("token", null);
        if (token != null)
            currentRequest.addHeader("Authorization", "Bearer " + token);

        Request newRequest = currentRequest.build();
        return chain.proceed(newRequest);
    }
}