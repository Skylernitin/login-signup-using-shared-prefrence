package com.example.testmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText login_txtemail, login_txtpassword;
    Button btn_login;
    TextView tv_signup;
    private static final String uname = "username";
    private static final String uemail = "useremail";
    private static final String upassword = "userpassword";
    private String TAG = getClass().getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_txtemail = findViewById(R.id.login_txtemail);
        login_txtpassword = findViewById(R.id.login_txtpassword);
        btn_login = findViewById(R.id.btn_login);
        tv_signup = findViewById(R.id.tv_signup);


        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("demo", MODE_PRIVATE);
                String email = login_txtemail.getText().toString();
                String password = login_txtpassword.getText().toString();
                String useremail = sp.getString(uemail, null);
                String userpassword = sp.getString(upassword, null);


                Log.i(TAG, "------onClick email : " + email);
                Log.i(TAG, "------onClick password : " + password);
                Log.i(TAG, "------onClick useremail : " + useremail);
                Log.i(TAG, "------onClick userpassword : " + userpassword);

                //Check if username, password is filled
                if (email.trim().length() > 0 && password.trim().length() > 0) {
                    if (email.equals(useremail) && password.equals(userpassword)) {
                        Toast.makeText(getApplicationContext(), "Login Sucessful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Invalid User", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Email/Password are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}