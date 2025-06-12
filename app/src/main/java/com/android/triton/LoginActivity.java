package com.android.triton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

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
                showDialog("All fields are required");
            } else {
                if (!UserStorage.userExists(this, enteredEmail)) {
                    showDialog("User does not exist. Please create a new account and then log in.");
                } else if (UserStorage.validateLogin(this, enteredEmail, enteredPassword)) {
                    JSONObject userDetails = UserStorage.getUserDetails(this, enteredEmail);
                    String name = userDetails.optString("name");
                    String phone = userDetails.optString("phone");

                    // Save session
                    SessionManager sessionManager = new SessionManager(this);
                    sessionManager.createLoginSession(enteredEmail);

                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", enteredEmail);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    showDialog("Invalid credentials");
                }
            }
        });

        signupBtn.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    // Show dialog function
    private void showDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Login Failed")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .show();
    }
}
