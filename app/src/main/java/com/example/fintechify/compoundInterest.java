package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class compoundInterest extends AppCompatActivity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_interest);
        back = (Button) findViewById(R.id.back);
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
        startActivity(intent);
    }

    public void goSimple(){
        Intent intent = new Intent(this, simpleInterest.class);
        startActivity(intent);
    }
}