package edu.ihm.vue.web_service;

import android.content.Context;
import android.content.SharedPreferences;

import edu.ihm.vue.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WebService {
    private static WebService INSTANCE = null;
    private final static String URL = "https://greentrack.puceaulytech.fr/";
    private final GreenTrackAPI service;

    private WebService(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(sharedPref))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebService.URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();

        this.service = retrofit.create(GreenTrackAPI.class);
    }

    public static synchronized WebService getInstance(Context context) {
        if (WebService.INSTANCE == null)
            WebService.INSTANCE = new WebService(context);

        return WebService.INSTANCE;
    }

    public GreenTrackAPI getService() {
        return this.service;
    }
}
