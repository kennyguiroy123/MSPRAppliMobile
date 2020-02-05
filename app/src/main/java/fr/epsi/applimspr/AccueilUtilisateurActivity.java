package fr.epsi.applimspr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AccueilUtilisateurActivity extends AppCompatActivity {
    private Button btnListPromo;
    private ImageView imageRetourAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilutilisateur_activity);
        btnListPromo = findViewById(R.id.buttonListPromo);
        btnListPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilUtilisateurActivity.this, ListPromoActivity.class);
                startActivity(intent);
            }
        });

        imageRetourAccueil = findViewById(R.id.imageViewBackAccueil);
        imageRetourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilUtilisateurActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
