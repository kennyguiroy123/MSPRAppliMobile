package fr.epsi.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class MainActivity extends DemoActivity {

    public static void display(DemoActivity activity){
        Intent intent=new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showBackBtn();
        setTitle("Ex1");
        final EditText editText=findViewById(R.id.editText);
        Button btnA=findViewById(R.id.buttonA);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.display(MainActivity.this,editText.getText().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode){
            case ImageActivity.K_I_RESULT_CODE_CLOSE:
                String msg=data.getExtras().getString("msg","");
                displayToast(msg);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
