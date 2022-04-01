package com.example.fintechify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.util.*;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button logIn;
    private String usersFile;
    private String usersFilePath;
    private TextView txtRegister;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = (Button) findViewById(R.id.logIn);
        txtRegister = findViewById(R.id.register);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);

        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputEmail = txtEmail.getText().toString();
                String inputPassword = txtPassword.getText().toString();
                System.out.println("Input Email - " + inputEmail);
                System.out.println("Password -" + inputPassword);
                System.out.println(sp.getString(inputEmail,""));

                if (sp.contains(inputEmail))
                {
                    String [] information = sp.getString(inputEmail,"").split("\\.");
                    if (information[0].equals(inputPassword)){
                        openHome();
                    }
                    else
                        Toast.makeText(MainActivity.this,"Invalid Password !",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Email is not registered !",Toast.LENGTH_LONG).show();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    public void openHome(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

}
