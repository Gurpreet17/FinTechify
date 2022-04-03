package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import java.text.NumberFormat;

public class simpleInterest extends AppCompatActivity {
    private Button back, calculate;
    private String email, balance;
    private TextView txtbalance;
    private SharedPreferences sp;
    private String[] verified;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_interest);
        back = (Button) findViewById(R.id.back);
        txtbalance = findViewById(R.id.balance);
        calculate = findViewById(R.id.calculate);

        sp = getApplicationContext().getSharedPreferences("myUserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtbalance.setText(balance);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        Button compound = (Button) findViewById(R.id.compoundInterest);
        compound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCompound();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simple();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, Interest.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
    public void goCompound(){
        Intent intent = new Intent(this, compoundInterest.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }

    public void simple(){
        TextView balView = (TextView) findViewById(R.id.balance);
        EditText rView = (EditText) findViewById(R.id.compoundRate);
        EditText yView = (EditText) findViewById(R.id.compoundTime);

        String r = rView.getText().toString();
        String y = yView.getText().toString();
        String s = balView.getText().toString();
        double rate = Double.parseDouble(r)/100;
        double years = Double.parseDouble(y);
        double start = Double.parseDouble(s.substring(1));
        System.out.println(start);
        double rateyears = (rate*years)/100;

        double sum = start * (1 + (rate*years));

        s = String.format("%.2f", sum);
        ((TextView) findViewById(R.id.newBalance)).setText(s);
        System.out.println(s);
    }

    
    
}