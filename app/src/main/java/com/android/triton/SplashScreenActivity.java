package com.android.triton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Splash screen layout

        SessionManager sessionManager = new SessionManager(this);

        new Handler().postDelayed(() -> {
            if (sessionManager.isLoggedIn()) {
                String email = sessionManager.getUserEmail();
                JSONObject userDetails = UserStorage.getUserDetails(this, email);
                String name = userDetails.optString("name");
                String phone = userDetails.optString("phone");

                Intent intent = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                startActivity(intent);
            } else {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }, 2500); // 2.5-second delay
    }
}
