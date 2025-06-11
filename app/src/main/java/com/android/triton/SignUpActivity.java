package com.android.triton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText name, email, phone, password;
    Button registerBtn;
    TextView txtSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.editName);
        txtSignIn = findViewById(R.id.txtSignIn);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        password = findViewById(R.id.editPassword);
        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(v -> {
            String userName = name.getText().toString().trim();
            String userEmail = email.getText().toString().trim();
            String userPhone = phone.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            if (userName.isEmpty() || userEmail.isEmpty() || userPhone.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                // Save to SharedPreferences
                getSharedPreferences("UserData", MODE_PRIVATE)
                        .edit()
                        .putString("name", userName)
                        .putString("email", userEmail)
                        .putString("phone", userPhone)
                        .putString("password", userPassword)
                        .apply();

                Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        txtSignIn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }
}

