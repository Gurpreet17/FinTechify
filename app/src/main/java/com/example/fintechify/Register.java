package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
    private Button btnRegister, logIn;
    private TextView txtEmail, txtPassword, txtName, txtAge;
    private String strName, strAge, strEmail, strPassword;
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
                String balance = "69";
                sp = getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                String information = strPassword + "." + strEmail + "." + strName + "." + balance;
                editor.putString(strEmail,information);
                editor.commit();
                System.out.println(sp.getAll());
                Toast.makeText(Register.this,"Successfully Registered",Toast.LENGTH_LONG).show();
            }
        });

        logIn = findViewById(R.id.logIn);
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLogin();
            }
        });

    }
    public void openLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(name, mode);
    }
}