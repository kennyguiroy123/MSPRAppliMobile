package fr.epsi.applimspr;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {
    private Button btnConnexion;
    final WebService ws = new WebService();
    Gson gson;
    private final String URL = "http://localhost:8000/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //se connecter

        btnConnexion = findViewById(R.id.buttonConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Envoi de la requête
                    InputStream inputStream = ws.sendRequest(new URL(URL));

                    // Vérification de l'inputStream
                    if(inputStream != null) {
                        // Lecture de l'inputStream dans un reader
                        InputStreamReader reader = new InputStreamReader(inputStream);

                        // Retourne la liste désérialisée par le moteur GSON
                        //TextView et = findViewById(R.id.textView3);
                        gson.fromJson(reader, new TypeToken<List<Point>>(){}.getType());
                        Intent intent = new Intent(MainActivity.this, AccueilUtilisateurActivity.class);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Log.e("WebService", "Impossible de rapatrier les données :(");
                }

            }
        });



    }


}



