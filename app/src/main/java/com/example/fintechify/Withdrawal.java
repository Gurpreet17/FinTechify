package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class Withdrawal extends AppCompatActivity {
    private Button back, withdrawal;
    private TextView txtBalance, txtAmount;
    private String [] verified;// = MainActivity.vertified;
    private String balance, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        back = (Button) findViewById(R.id.back);
        txtBalance = findViewById(R.id.balance);
        txtAmount = findViewById(R.id.depositAmount);
        withdrawal = findViewById(R.id.withdrawal);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myUserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtBalance.setText(balance);

        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double amountWd = Double.parseDouble(txtAmount.getText().toString());
                if (amountWd > Double.parseDouble(verified[3])) {
                    Toast.makeText(Withdrawal.this, "That amount exceeds your balance!", Toast.LENGTH_SHORT).show();
                } else {
                    double newBalance = Double.parseDouble(verified[3]) - amountWd;

                    verified[3] = newBalance + "";
                    txtBalance.setText(defaultFormat.format(newBalance));

                    String info = "";
                    for (int x = 0; x < verified.length - 1; x++)
                        info += verified[x] + ";";
                    info += verified[3];

                    System.out.println("info - " + info);
                    editor.putString(email, info).commit();
                    Toast.makeText(Withdrawal.this, "Amount deposited", Toast.LENGTH_SHORT).show();
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