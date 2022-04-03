package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

public class compoundInterest extends AppCompatActivity {
    private Button back, calculate;
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
        calculate = findViewById(R.id.compoundButton);

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

        Button simple = (Button) findViewById(R.id.simpleInterest);
        simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSimple();
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compound();
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

    public void compound()
    {
        TextView balView = (TextView) findViewById(R.id.balance);
        EditText rView = (EditText) findViewById(R.id.compoundRate);
        EditText yView = (EditText) findViewById(R.id.compoundTime);
        EditText tView = (EditText) findViewById(R.id.numberCompound);
        String r = rView.getText().toString();
        String y = yView.getText().toString();
        String s = balView.getText().toString();
        String t = tView.getText().toString();
        if (r.equals("") || y.equals("") || s.equals("") || t.equals(""))
        {
            Toast.makeText( compoundInterest.this,"Invalid Inputs",Toast.LENGTH_SHORT).show();
        }
        else{
            double rate = Double.parseDouble(r)/100;
            int years = Integer.parseInt(y);
            double start = Double.parseDouble(verified[3]);
            int times = Integer.parseInt(t);
            int nt = times*years;
            double rOverN = rate/times;
            double compound = start*Math.pow((1+rOverN),nt);
            s = String.format("%.2f", compound);
            ((TextView) findViewById(R.id.balance2)).setText(s);
        }
    }
}