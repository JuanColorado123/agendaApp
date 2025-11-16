package com.example.agendaapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NoticiaTextView extends AppCompatTextView {

    private static final String TAG = "NoticiaTextView";
    private ProgressBar progressBar;

    public NoticiaTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // La carga se iniciará cuando se adjunte a la ventana
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        cargarNoticia();
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    private void cargarNoticia() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        OkHttpClient client = new OkHttpClient();

        String url = "https://newsapi.org/v2/everything?q=tesla&from=2025-10-16" +
                "&sortBy=publishedAt&apiKey=31c2890c64ff41d9b22a2472d43b0999";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "Mozilla/5.0")
                .addHeader("Accept", "application/json")
                .build();

        new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful()) {
                    throw new Exception("Código HTTP: " + response.code());
                }

                String json = response.body().string();
                JSONObject obj = new JSONObject(json);

                if (obj.getString("status").equals("ok")) {
                    JSONArray articles = obj.getJSONArray("articles");

                    if (articles.length() > 0) {
                        JSONObject firstArticle = articles.getJSONObject(0);
                        String titulo = firstArticle.getString("title");
                        String fuente = firstArticle.getJSONObject("source").getString("name");

                        String noticiaFormateada = "📢 " + titulo +
                                "\n\n🔹 Fuente: " + fuente;

                        final String textoFinal = noticiaFormateada.length() > 150 ?
                                noticiaFormateada.substring(0, 150) + "..." : noticiaFormateada;

                        post(() -> {
                            setText(textoFinal);
                            if (progressBar != null) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    } else {
                        throw new Exception("No hay artículos disponibles");
                    }
                } else {
                    throw new Exception("Error en la respuesta de la API");
                }

            } catch (Exception e) {
                Log.e(TAG, "Error cargando noticia", e);
                post(() -> {
                    setText("📰 No hay noticias disponibles en este momento\n\nIntenta más tarde.");
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        }).start();
    }
}