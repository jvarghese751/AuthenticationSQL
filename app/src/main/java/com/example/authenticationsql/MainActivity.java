package com.example.authenticationsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtUsername, txtPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        txtUsername = (EditText) findViewById(R.id.usernametxt);
        txtPassword = (EditText) findViewById(R.id.password_txt);

        btnRegister = (Button) findViewById(R.id.register_btn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if (username.equals("") | password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!isValidEmail(username)) {
                        Toast.makeText(getApplicationContext(), "Invalid Email Format!", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean checkUsername = db.checkUsername(username);
                        if (checkUsername == true) {
                            Boolean insert = db.insert(username, password);
                            Toast.makeText(getApplicationContext(), "Register Successful!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "User Already Exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
        //btnLogin = (Button)findViewByI(R.id.btnLogin);
        private boolean isValidEmail(String username){
            return username.contains("@");
        }
    }

