package com.android.triton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button loginBtn;
    TextView signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        loginBtn = findViewById(R.id.btnLogin);
        signupBtn = findViewById(R.id.btnSignUp);

        loginBtn.setOnClickListener(v -> {
            String enteredEmail = username.getText().toString().trim();
            String enteredPassword = password.getText().toString().trim();

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                // Get saved data
                SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                String savedEmail = prefs.getString("email", null);
                String savedPassword = prefs.getString("password", null);

                if (savedEmail != null && savedPassword != null
                        && enteredEmail.equals(savedEmail)
                        && enteredPassword.equals(savedPassword)) {

                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("name", prefs.getString("name", ""));
                    intent.putExtra("email", savedEmail);
                    intent.putExtra("phone", prefs.getString("phone", ""));
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                } else {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });


        signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}
