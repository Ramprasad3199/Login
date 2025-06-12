package com.android.triton;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView welcomeText = findViewById(R.id.textWelcome);
        TextView emailText = findViewById(R.id.textEmail);
        TextView phoneText = findViewById(R.id.textPhone);
        TextView textLogout = findViewById(R.id.textLogout);

        // Get data from Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        welcomeText.setText("Welcome, " + name + "!");
        emailText.setText("Email: " + email);
        phoneText.setText("Phone: " + phone);

        textLogout.setOnClickListener(v -> showLogoutDialog());

    }
    private void showLogoutDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Perform logout
                    SessionManager sessionManager = new SessionManager(this);
                    sessionManager.logoutUser();

                    Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                })
                .setNegativeButton("No", null)
                .setCancelable(false)
                .show();
    }
}

