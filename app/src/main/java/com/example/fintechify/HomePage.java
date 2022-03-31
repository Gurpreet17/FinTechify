package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button logOut, deposit, withdrawal, interest, taxes, sat, transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logOut = (Button) findViewById(R.id.logOut);
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
                openSAT();
            }
        });

        transfer = (Button) findViewById(R.id.transfer);
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExpenses();
            }
        });
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openDeposit() {
        Intent intent = new Intent(this, Deposit.class);
        startActivity(intent);
    }
    public void openWithdrawal() {
        Intent intent = new Intent(this, Withdrawal.class);
        startActivity(intent);
    }
    public void openInterest() {
        Intent intent = new Intent(this, Interest.class);
        startActivity(intent);
    }
    public void openTaxes() {
        Intent intent = new Intent(this, Taxes.class);
        startActivity(intent);
    }
    public void openSAT() {
        Intent intent = new Intent(this, Convert.class);
        startActivity(intent);
    }
    public void openExpenses() {
        Intent intent = new Intent(this, Transfer.class);
        startActivity(intent);
    }
}