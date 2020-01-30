package fr.epsi.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends DemoActivity {

    public static final int K_I_RESULT_CODE_CLOSE=222;

    public static void display(AppCompatActivity activity,String title){
        Intent intent=new Intent(activity,ImageActivity.class);
        intent.putExtra("title",title);
        activity.startActivityForResult(intent,0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        showBackBtn();
        setTitle("Image");
        final EditText editText=findViewById(R.id.editTextMsg);
        Button btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data=new Intent();
                data.putExtra("msg",editText.getText().toString());
                setResult(K_I_RESULT_CODE_CLOSE,data);
                finish();
            }
        });
        String str= getIntent().getExtras().getString("title","");
        displayToast(str);
    }
}
