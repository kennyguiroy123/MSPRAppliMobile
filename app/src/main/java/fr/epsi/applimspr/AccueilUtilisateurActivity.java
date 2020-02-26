package fr.epsi.applimspr;


import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AccueilUtilisateurActivity extends AppliActivity implements View.OnClickListener {
    private IntentIntegrator qrScan;
    public String token = MainActivity.token;
    public Character id_reduction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueilutilisateur_activity);
        showBackBtn();
        qrScan = new IntentIntegrator(this).setCaptureActivity(AnyOrientationCaptureActivity.class); //initialiser un objet scanner
        qrScan.setOrientationLocked(false);
        Button btnQrCode = findViewById(R.id.buttonQrCode);
        btnQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lancerCamera();
            }
        });
        // voir liste des promos
        Button btnListPromo = findViewById(R.id.buttonListPromo);
        btnListPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherListePromotions();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        String text_qrcode = result.getContents();
        if(text_qrcode == null){
            return;
        }
        Integer id = text_qrcode.length()-1;
        if (result.getContents() != null) {
            if (result.getContents().contains("10.0.2.2:8000")) {
                id_reduction =text_qrcode.charAt(id);
                new fetchData().execute();
            }else{
                openDialog("Oops","Cette réduction n'est pas valide à GoStyle","OK");
                afficherListePromotions();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Annulation!", Toast.LENGTH_SHORT).show();
            afficherListePromotions();
        }
    }

    public void lancerCamera(){
        qrScan.initiateScan();
    }
    public void afficherListePromotions(){
        Intent intent = new Intent(this, ListPromoActivity.class);
        startActivity(intent);
    }
    public void openDialog(String title,String message, String button){
        DialogPopup dialogue = new DialogPopup(title,message,button);
        dialogue.show(getSupportFragmentManager(),title);
    }

    public  class fetchData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            String result = null;
            try {
                URL url = new URL("http://10.0.2.2:8000/getReduct");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput( true );
                conn.setInstanceFollowRedirects( false );
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty( "Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setRequestProperty( "charset", "utf-8");
                conn.setUseCaches(false);
                String jsonInputString = "{\"id\":"+id_reduction+",\"token\":\""+token+"\"}";
                try(OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                conn.connect();
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(inputStream);
                    StringBuilder stringBuilder = new StringBuilder();
                    String temp;
                    while((temp = reader.readLine())!=null){
                        stringBuilder.append(temp);
                    }
                    result = "OK";
                    //stringBuilder.toString();
                    }
                else if(conn.getResponseCode() == 208){ //208 : already reported
                    result = "promotion déjà scannée";
                }
                else{
                    return "error!";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.equals("OK")){
            openDialog("Félicitation","Une réduction a été ajouté à votre compte","OK");
            //afficherListePromotions();
            }
            else if(s.equals("promotion déjà scannée")){
                openDialog("Déjà-vu ","Cette réduction est déjà présente dans votre compte","OK");
            }
            else
                System.out.println("erreur 500 - After Posting");
                return;
    }
    }
}