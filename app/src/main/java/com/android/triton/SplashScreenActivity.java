package com.android.triton;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // Splash screen layout

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, 2500); // 2-second delay
    }
}
