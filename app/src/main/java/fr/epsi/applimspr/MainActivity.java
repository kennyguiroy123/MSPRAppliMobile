package fr.epsi.applimspr;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import fr.epsi.applimspr.model.Utilisateur;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity {
    private Button btnConnexion;
    private EditText login;
    private EditText password;
    private String token;
    private String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


        btnConnexion = findViewById(R.id.buttonConnexion);
        login = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);


        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr="http://10.0.2.2:8000/test";
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(password.toString().getBytes(StandardCharsets.UTF_8));
                    pwd = hash.toString();
                } catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
                JsonArray param = new JsonArray();
                JsonObject username = new JsonObject();
                username.addProperty("username",login.toString());
                param.add(username);
                JsonObject p = new JsonObject();
                p.addProperty("password",pwd);
                param.add(p);
                new WebService(urlStr, new WebService.HttpAsyTaskListener() {
                    @Override
                    public void webServiceDone(String result) {
                        initData(result);
                    }

                    @Override
                    public void webServiceError(Exception e) {
                        displayToast(e.getMessage());
                    }
                }).execute();
            }
        });



    }
    private void initData(String data) {

        //JSONObject jsonObject;
        //jsonObject=new JSONObject(data);
        //JSONArray jsonArray=jsonObject.getJSONArray("items");
        Utilisateur user=new Utilisateur(login.toString(),password.toString(),token);



    }
    protected void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}



