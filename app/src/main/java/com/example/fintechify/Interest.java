package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class Interest extends AppCompatActivity {
    private Button back, simple, compound;
    private TextView txtBalance;
    private SharedPreferences sp;
    private String [] verified;
    private String balance, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        back = (Button) findViewById(R.id.back);
        txtBalance = findViewById(R.id.balance);

        sp = getApplicationContext().getSharedPreferences("myUserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtBalance.setText(balance);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        simple = (Button) findViewById(R.id.simple);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSimple();
            }
        });

        compound = (Button) findViewById(R.id.compound);
        compound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCompound();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
    public void goSimple(){
        Intent intent = new Intent(this, simpleInterest.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
    public void goCompound(){
        Intent intent = new Intent(this, compoundInterest.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
}