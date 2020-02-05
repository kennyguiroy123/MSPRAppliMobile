package fr.epsi.applimspr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnConnexion;

    public static void display(AccueilUtilisateurActivity activity){
        Intent intent=new Intent(activity,AccueilUtilisateurActivity.class);
        activity.startActivityForResult(intent,0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        btnConnexion = findViewById(R.id.buttonConnexion);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.display(AccueilUtilisateurActivity.this);
            }
        });
    }

    public void openActivityUtilisateur(){
        Intent accueilUtilisateur = new Intent(this, AccueilUtilisateurActivity.class);
        startActivity(accueilUtilisateur);
    }
}
