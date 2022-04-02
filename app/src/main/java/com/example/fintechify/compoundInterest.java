package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;

public class compoundInterest extends AppCompatActivity {
    private Button back;
    private TextView txtbalance;
    private SharedPreferences sp;
    private String [] verified;
    private String balance, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_interest);
        back = (Button) findViewById(R.id.back);
        txtbalance = findViewById(R.id.balance);

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

        Button simple = (Button) findViewById(R.id.calculate);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSimple();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, Interest.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }

    public void goSimple(){
        Intent intent = new Intent(this, simpleInterest.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
}