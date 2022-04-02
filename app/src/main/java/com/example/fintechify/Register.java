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

        sp = getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                strEmail = txtEmail.getText().toString();
                strPassword = txtPassword.getText().toString();
                strAge = txtAge.getText().toString();
                strName = txtName.getText().toString();

                String balance = "0.00";
                if(sp.contains(strEmail)){
                   Toast.makeText(Register.this,"Email is already registered!",Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(strAge) < 16){
                    Toast.makeText(Register.this,"Must be over 16 to register an account!",Toast.LENGTH_SHORT).show();
                } else{
                    String information = strPassword + ";" + strEmail + ";" + strName + ";" + balance;
                    editor.putString(strEmail, information).commit();
                    Toast.makeText( Register.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                    openLogin();
                }
                // System.out.println("sp - " + sp.getAll());
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
        intent.putExtra("userInformation",txtEmail.getText().toString());
        startActivity(intent);
    }
}