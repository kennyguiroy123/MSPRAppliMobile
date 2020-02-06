package fr.epsi.applimspr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AccueilUtilisateurActivity extends AppliActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilutilisateur_activity);
        showBackBtn();
        //lancer le Scanner du QRCode
        Button btnQrCode = findViewById(R.id.buttonQrCode);
        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerCamera();
            }
        });
        //voir liste des promos
        Button btnListPromo = findViewById(R.id.buttonListPromo);
        btnListPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilUtilisateurActivity.this, ListPromoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void lancerCamera(){
        Intent intent2 = new Intent(this,ScannerQR.class);
        startActivity(intent2);
    }

}
