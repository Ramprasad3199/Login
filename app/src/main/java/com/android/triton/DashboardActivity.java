package com.android.triton;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView welcomeText = findViewById(R.id.textWelcome);
        TextView emailText = findViewById(R.id.textEmail);
        TextView phoneText = findViewById(R.id.textPhone);

        // Get data from Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");

        welcomeText.setText("Welcome, " + name + "!");
        emailText.setText("Email: " + email);
        phoneText.setText("Phone: " + phone);
    }

}

