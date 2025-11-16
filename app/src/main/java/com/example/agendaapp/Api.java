package com.example.agendaapp;

import android.app.Activity;
import android.widget.TextView;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {

    public static void cargarNoticia(Activity activity, TextView tvNoticias) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.quotable.io/random")
                .build();

        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();
                String json = response.body().string();

                JSONObject obj = new JSONObject(json);
                String contenido = obj.getString("content");

                activity.runOnUiThread(() -> tvNoticias.setText(contenido));

            } catch (Exception e) {
                activity.runOnUiThread(() -> tvNoticias.setText("error cargando datos"));
            }
        }).start();
    }
}
