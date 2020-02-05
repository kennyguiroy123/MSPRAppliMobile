package fr.epsi.applimspr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilUtilisateurActivity extends AppCompatActivity {
    private Button btnListPromo;

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

    }
}
