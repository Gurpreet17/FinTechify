package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.text.NumberFormat;

public class Taxes extends AppCompatActivity {
    private Button back, calculate;
    private TextView txtBalance, result, rate;
    private String [] verified;
    private Switch ontarioTS, fedTS;
    private SharedPreferences sp;
    private String balance, email;
    private double onTax, fedTax, sum, start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxes);
        back = (Button) findViewById(R.id.back);
        txtBalance = findViewById(R.id.balance);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);
        rate = findViewById(R.id.rate);
        ontarioTS = findViewById(R.id.ONTS);
        fedTS = findViewById(R.id.fedTS);


        sp = getApplicationContext().getSharedPreferences("myUserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        start = Double.parseDouble(verified[3]);
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtBalance.setText(balance);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ontarioTS.isChecked()) {
                    fedTS.setChecked(false);
                    if (start <= 45142) {
                        onTax = start * 0.0505;
                        sum = start - onTax;
                    } else if (start > 45142 && start <= 90287) {
                        onTax = start * 0.0915;
                        sum = start - onTax;
                    } else if (start > 90287 && start <= 150000) {
                        onTax = start * 0.1116;
                        sum = start - onTax;
                    } else if (start > 150000 && start <= 220000) {
                        onTax = start * 0.1216;
                        sum = start - onTax;
                    } else {
                        onTax = start * 0.1313;
                        sum = start - onTax;
                    }
                }
                else if(fedTS.isChecked()) {
                    ontarioTS.setChecked(false);
                    if (start <= 49020) {
                        fedTax = start * 0.15;
                        sum = start - fedTax;
                    } else if (start > 49020 && start <= 98040) {
                        fedTax = start * 0.205;
                        sum = start - fedTax;
                    } else if (start > 98040 && start <= 151978) {
                        fedTax = start * 0.26;
                        sum = start - fedTax;
                    } else if (start > 151978 && start <= 216511) {
                        fedTax = start * 0.29;
                        sum = start - fedTax;
                    } else {
                        fedTax = start * 0.33;
                        sum = start - fedTax;
                    }
                }
                else if(!(fedTS.isChecked() && ontarioTS.isChecked())) {
                    try {
                        onTax = start * (Double.parseDouble(rate.getText().toString())/100);
                    } catch (Exception e){
                        onTax = start * 0.13;
                    }
                    sum = start - onTax;
                }
                String s = String.format("%.2f", sum);
                result.setText(s);
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
}