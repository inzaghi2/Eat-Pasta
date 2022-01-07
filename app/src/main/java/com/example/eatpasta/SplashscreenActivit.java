package com.example.eatpasta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashscreenActivit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.activity_splashscreen);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        final Handler handler= new Handler();
        handler.postDelayed( new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent (getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 3000L); // 3000L = 3 detik
    }
}
