package fr.epsi.applimspr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
        Button btnQrCode = findViewById(R.id.buttonQrCode);
        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerCamera();
            }
        });
        btnListPromo = findViewById(R.id.buttonListPromo);
        btnListPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilUtilisateurActivity.this, ListPromoActivity.class);
                startActivity(intent);
            }
        });
    }


    public void lancerCamera(){
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(intent2);
        int REQUEST_ID_IMAGE_CAPTURE = 100;
        //Start camera and wait for the results.
        this.startActivityForResult(intent2, REQUEST_ID_IMAGE_CAPTURE);
    }

        imageRetourAccueil = findViewById(R.id.imageViewBackAccueil);
        imageRetourAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilUtilisateurActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




}
