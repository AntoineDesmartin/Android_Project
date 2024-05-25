package edu.ihm.vue.web_service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WebService {
    private static WebService INSTANCE = null;
    private final static String URL = "http://10.212.118.125:3333/";
    private GreenTrackAPI service = null;

    private WebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebService.URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.service = retrofit.create(GreenTrackAPI.class);
    };

    public static synchronized WebService getInstance() {
        if (WebService.INSTANCE == null)
            WebService.INSTANCE = new WebService();

        return WebService.INSTANCE;
    }

    public GreenTrackAPI getService() {
        return this.service;
    }
}
