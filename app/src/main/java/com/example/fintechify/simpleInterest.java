package com.example.fintechify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class simpleInterest extends AppCompatActivity {
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_interest);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBack();
            }
        });
        Button compound = (Button) findViewById(R.id.compound);
        compound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCompound();
            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(this, Interest.class);
        startActivity(intent);
    }
    public void goCompound(){
        Intent intent = new Intent(this, compoundInterest.class);
        startActivity(intent);
    }
    
    
}