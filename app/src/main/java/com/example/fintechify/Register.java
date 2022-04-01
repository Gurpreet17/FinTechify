package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import android.os.Parcel;
import android.os.Parcelable;



public class Register extends AppCompatActivity {
    private Button btnRegister;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView txtName;
    private TextView txtAge;
    private String strName;
    private String strAge;
    private String strEmail;
    private String strPassword;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.register);
        txtName = findViewById(R.id.name);
        txtAge = findViewById(R.id.age);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);

        btnRegister.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){
                strEmail = txtEmail.getText().toString();
                strPassword = txtPassword.getText().toString();
                strAge = txtAge.getText().toString();
                strName = txtName.getText().toString();
                sp = getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String information = strPassword + "." + strName + "." + strAge;
                editor.putString(strEmail,information);
                editor.commit();
                System.out.println(sp.getAll());
                Toast.makeText(Register.this,"Successfully Registered",Toast.LENGTH_LONG).show();
            }
        });
    }
}