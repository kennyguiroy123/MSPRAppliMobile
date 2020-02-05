package fr.epsi.applimspr;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AppliActivity extends AppCompatActivity implements View.OnClickListener {

    protected void showBackBtn(){
        ImageView imageView=findViewById(R.id.imageViewBackAccueil);
        if(imageView!=null) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewBackAccueil:
                finish();
                break;
        }
    }
}
