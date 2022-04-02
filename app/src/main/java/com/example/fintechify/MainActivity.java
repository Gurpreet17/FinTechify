package com.example.fintechify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button logIn, register;
    private TextView txtEmail;
    private TextView txtPassword;
    public static String[] vertified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = findViewById(R.id.logIn);
        register = findViewById(R.id.register);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);

        //sp.edit().clear().commit();

        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String inputEmail = txtEmail.getText().toString();
                String inputPassword = txtPassword.getText().toString();

                if (sp.contains(inputEmail))
                {
                    String [] information = sp.getString(inputEmail,"").split("\\;");
                    if (information.length > 0 && information[0].equals(inputPassword)){
                        vertified = information;
                        System.out.println(vertified[0] + " " + vertified[1] + " " + vertified[2] + " " + vertified[3]);
                        openHome(); }
                    else
                        Toast.makeText(MainActivity.this,"Invalid Password !",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"Email is not registered !",Toast.LENGTH_LONG).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    public void openHome(){
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("userInformation",txtEmail.getText().toString());
        startActivity(intent);
    }

    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}