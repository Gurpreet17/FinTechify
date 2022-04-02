package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class Deposit extends AppCompatActivity {
    private Button back, btnDeposit;
    private TextView txtBalance, txtAmount;
    private String [] verified;// = MainActivity.vertified;
    private String balance,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        back = findViewById(R.id.back);
        btnDeposit = findViewById(R.id.deposit);
        txtBalance = findViewById(R.id.balance);
        txtAmount = findViewById(R.id.depositAmount);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        SharedPreferences.Editor editor = sp.edit();
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));

        txtBalance.setText(balance);
        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amountDeposit = Double.parseDouble(txtAmount.getText().toString());
                if (amountDeposit < 0) {
                    Toast.makeText(Deposit.this, "The deposit cannot be less than 0!", Toast.LENGTH_SHORT).show();
                } else {
                    double newBalance = Double.parseDouble(verified[3]) + amountDeposit;

                    verified[3] = newBalance + "";
                    txtBalance.setText(defaultFormat.format(newBalance));

                    String info = "";
                    for (int x = 0; x < verified.length - 1; x++)
                        info += verified[x] + ";";
                    info += verified[3];

                    //System.out.println("info - " + info);
                    editor.putString(email, info).commit();
                    Toast.makeText(Deposit.this, "Amount deposited", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });

    }

    public void goBack(){
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
}