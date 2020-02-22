package fr.epsi.applimspr;

import fr.epsi.applimspr.model.Utilisateur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    private Button btnConnexion;
    private EditText login;
    private EditText password;
    public static String token;
    private String pwd;
    //private JsonPlaceHolderApi jsonPlaceHolderApi;
    private String urlStr="http://10.0.2.2:8000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        btnConnexion = findViewById(R.id.buttonConnexion);
        login = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        final TextView textViewResult = findViewById(R.id.textViewResult);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlStr)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final JsonPlaceHolderApi service = retrofit.create(JsonPlaceHolderApi.class);

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer hexString = new StringBuffer();
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] hash = digest.digest(password.getText().toString().getBytes(StandardCharsets.UTF_8));
                for (int i = 0; i < hash.length; i++) {
                    if ((0xff & hash[i]) < 0x10) {
                        hexString.append("0"
                                + Integer.toHexString((0xFF & hash[i])));
                    } else {
                        hexString.append(Integer.toHexString(0xFF & hash[i]));
                    }
                }
                pwd = hexString.toString();

                Map<String, String> parameters = new HashMap<>();
                parameters.put("username", login.getText().toString());
                parameters.put("password", pwd);


                //Call<Utilisateur> call = service.connexion(login.getText().toString(),pwd);
                Call<Utilisateur> call = service.connexion(parameters);

                call.enqueue(new Callback<Utilisateur>() {
                    @Override
                    public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {

                        if (!response.isSuccessful()) {
                            textViewResult.setText("Code: " + response.code()+"msg :"+response.message()+"\n "+response.errorBody().byteStream().toString());
                            return;
                        }
                        Object user = response.body();
                        token = response.body().getToken();
                        displayToast("User token :"+token);
                        //textViewResult.setText("User token :"+response.body().getToken());
                        logIn();
                    }

                    @Override
                    public void onFailure(Call<Utilisateur> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });
            }
        });
    }
    public void logIn(){
        Intent intent = new Intent(this,AccueilUtilisateurActivity.class);
        startActivity(intent);
    }
    protected void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}