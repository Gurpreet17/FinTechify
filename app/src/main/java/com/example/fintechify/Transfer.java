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
import com.example.fintechify.MainActivity.*;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class Transfer extends AppCompatActivity {
    private Button back, calculate;
    private TextView txtBalance;
    private SharedPreferences sp;
    private TextInputEditText tt, amt;
    private String [] verified;
    private String balance, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        sp = getApplicationContext().getSharedPreferences("myUserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        tt = findViewById(R.id.transfer_to);
        amt = findViewById(R.id.transfer_amount);
        calculate = findViewById(R.id.calculate);
        txtBalance = findViewById(R.id.balance);

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtBalance.setText(balance);

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = tt.getText().toString();
                String amount = amt.getText().toString();

                if (sp.contains(email)) {
                    String[] information = sp.getString(email, "").split("\\.");
                    if(Double.parseDouble(verified[3]) >= Double.parseDouble(amount)){ ;
                        information[3] =  Double.parseDouble(information[3]) + Double.parseDouble(amount) + "";
                        verified[3] = Double.parseDouble(verified[3]) - Double.parseDouble(amount) + "";

                        String update = "", update2 = "";
                        for(int i = 0; i < information.length-1; i++){
                            update+=information[i]+";";
                            update2+=verified[i]+";";
                        }

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(email, update);
                        editor.putString(verified[1], update2);
                        editor.commit();
                    }
                }
                else { Toast.makeText(Transfer.this,"User does not exist !",Toast.LENGTH_LONG).show(); }
            }
        });

        back = (Button) findViewById(R.id.back);
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