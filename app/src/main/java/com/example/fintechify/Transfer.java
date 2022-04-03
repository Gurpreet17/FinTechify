package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class Transfer extends AppCompatActivity {
    private Button back, calculate;
    private TextView txtBalance;
    private SharedPreferences sp;
    private EditText amt, tt;
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
        calculate = findViewById(R.id.simpleInterest);
        txtBalance = findViewById(R.id.balance);

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        balance = defaultFormat.format(Double.parseDouble(verified[3]));
        txtBalance.setText(balance);

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String temail = tt.getText().toString();
                String amount = amt.getText().toString();

                if (sp.contains(temail) && !temail.equals(email)) {
                    String[] information = sp.getString(temail, "").split("\\;");
                    if(Double.parseDouble(verified[3]) >= Double.parseDouble(amount)){ ;
                        information[3] =  Double.parseDouble(information[3]) + Double.parseDouble(amount) + "";
                        verified[3] = Double.parseDouble(verified[3]) - Double.parseDouble(amount) + "";

                        String update = "", update2 = "";
                        for(int i = 0; i < information.length-1; i++){
                            update+=information[i]+";";
                            update2+=verified[i]+";";
                        }
                        update+=information[3];
                        update2+=verified[3];

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(temail, update);
                        editor.putString(verified[1], update2);
                        editor.commit();

                        balance = defaultFormat.format(Double.parseDouble(verified[3]));
                        txtBalance.setText(balance);
                    } else {
                        Toast.makeText(Transfer.this,"That amount exceeds your balance!",Toast.LENGTH_LONG).show();
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