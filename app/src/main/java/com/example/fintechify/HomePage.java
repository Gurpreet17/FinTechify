package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;
import com.example.fintechify.MainActivity.*;

public class HomePage extends AppCompatActivity {
    private Button logOut, deposit, withdrawal, interest, taxes, sat, transfer;
    private TextView txtWelcome, txtBalance;
    private String email, balance;
    private String[] verified = MainActivity.vertified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logOut = (Button) findViewById(R.id.logOut);
        deposit = (Button) findViewById(R.id.deposit);
        txtWelcome = findViewById(R.id.welcome);
        txtBalance = findViewById(R.id.balance);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtWelcome.setText(String.format("Welcome, %s", verified[2]));
        txtBalance.setText(balance);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });

        deposit = (Button) findViewById(R.id.deposit);
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeposit();
            }
        });

        withdrawal = (Button) findViewById(R.id.withdrawal);
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWithdrawal();
            }
        });

        interest = (Button) findViewById(R.id.interest);
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInterest();
            }
        });

        taxes = (Button) findViewById(R.id.taxes);
        taxes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTaxes();
            }
        });

        sat = (Button) findViewById(R.id.conversion);
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConvert();
            }
        });

        transfer = (Button) findViewById(R.id.transfer);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransfer();
            }
        });
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openDeposit() {
        Intent intent = new Intent(this, Deposit.class);
        intent.putExtra("userInformation", email);
        startActivity(intent);
    }
    public void openWithdrawal() {
        Intent intent = new Intent(this, Withdrawal.class);
        intent.putExtra("userInformation",email);
        startActivity(intent);
    }
    public void openInterest() {
        Intent intent = new Intent(this, Interest.class);
        intent.putExtra("userInformation",email);
        startActivity(intent);
    }
    public void openTaxes() {
        Intent intent = new Intent(this, Taxes.class);
        intent.putExtra("userInformation",email);
        startActivity(intent);
    }
    public void openConvert() {
        Intent intent = new Intent(this, Convert.class);
        intent.putExtra("userInformation",email);
        startActivity(intent);
    }
    public void openTransfer() {
        Intent intent = new Intent(this, Transfer.class);
        intent.putExtra("userInformation",email);
        startActivity(intent);
    }
}