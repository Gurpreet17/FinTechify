package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class Taxes extends AppCompatActivity {
    private Button back;
    private TextView txtBalance;
    private String [] verified;
    private String balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxes);
        back = (Button) findViewById(R.id.back);
        txtBalance = findViewById(R.id.balance);
        verified = getIntent().getExtras().getStringArray("userInformation");
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtBalance.setText(balance);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}