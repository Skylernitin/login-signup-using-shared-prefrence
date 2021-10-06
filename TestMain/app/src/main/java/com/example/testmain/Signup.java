package com.example.testmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.basgeekball.awesomevalidation.*;

import android.widget.*;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.*;

public class Signup extends AppCompatActivity {
    TextInputEditText signup_txtemail, signup_txtpassword, signup_txtperson;
    Button btn_signup;
    TextView tv_login;
    private AwesomeValidation awesomeValidation;
    private static final String uname = "username";
    private static final String uemail = "useremail";
    private static final String upassword = "userpassword";
    private String TAG = getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        signup_txtperson = findViewById(R.id.signup_txtperson);
        signup_txtemail = findViewById(R.id.signup_txtemail);
        signup_txtpassword = findViewById(R.id.signup_txtpassword);
        btn_signup = findViewById(R.id.btn_signup);
        tv_login = findViewById(R.id.tv_login);

        //adding validation to edittexts
        awesomeValidation.addValidation(this, R.id.signup_txtperson, "^[A-Za-z]+$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.signup_txtemail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.signup_txtpassword, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", R.string.passworderror);

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sperson = signup_txtperson.getText().toString();
                String semail = signup_txtemail.getText().toString();
                String spassword = signup_txtpassword.getText().toString();
                SharedPreferences sp = getSharedPreferences("demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(uname, sperson);
                editor.putString(uemail, semail);
                editor.putString(upassword, spassword);
                editor.commit();

                Log.i(TAG, "sperson :" + sperson);
                Log.i(TAG, "semail :" + semail);
                Log.i(TAG, "spassword :" + spassword);

                //Check if username, password is filled
                if (sperson.trim().length() > 0 && semail.trim().length() > 0 && spassword.trim().length() > 0) {
                    //Check name,email,password are valid
                    if (awesomeValidation.validate()) {
                        Toast.makeText(Signup.this, "Signup Sucessfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Signup.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(Signup.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}