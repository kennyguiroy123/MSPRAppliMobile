package fr.epsi.applimspr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ListPromoActivity extends AppCompatActivity {
    private Button btnRetourAccueil;
    private ImageView imageRetourAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listpromo_activity);

        btnRetourAccueil = findViewById(R.id.buttonAccueilPromo);
        btnRetourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListPromoActivity.this, AccueilUtilisateurActivity.class);
                startActivity(intent);
            }
        });

        imageRetourAccueil = findViewById(R.id.imageViewBackAccueil);
        imageRetourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListPromoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
