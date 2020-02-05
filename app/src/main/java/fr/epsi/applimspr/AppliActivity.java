package fr.epsi.applimspr;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AppliActivity extends AppCompatActivity{
    protected AppliApp appliApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appliApp=(AppliApp) getApplication();
    }

}
