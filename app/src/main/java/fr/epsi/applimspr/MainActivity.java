package fr.epsi.applimspr;



import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import fr.epsi.applimspr.model.Utilisateur;


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
                new WebService(urlStr, login.toString(), pwd, new WebService.HttpAsyTaskListener() {
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
        //Utilisateur user=new Utilisateur(login.toString(),password.toString(),token);



    }
    protected void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}



