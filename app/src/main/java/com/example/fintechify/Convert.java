package com.example.fintechify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Convert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button back, calculate;
    private TextView results;
    private Spinner droplist, droplist2;
    private EditText inputAmount;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sp;
    private String [] verified;
    private String email;

    private double api;
    List<String> array = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        sp = getApplicationContext().getSharedPreferences("myUserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        email = getIntent().getExtras().getString("userInformation");
        verified = sp.getString(email,"").split("\\;");

        droplist = findViewById(R.id.droplist);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        droplist.setOnItemSelectedListener(this);

        droplist2 = findViewById(R.id.droplist2);
        droplist2.setOnItemSelectedListener(this);

        inputAmount = findViewById(R.id.compoundRate);
        calculate = findViewById(R.id.simpleInterest);
        fetchData("CAD", "CAD", 1);
        calculate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                try {
                    api = Double.parseDouble(inputAmount.getText().toString());
                } catch (Exception e){
                    api = 1.00;
                } finally {
                    fetchData(droplist.getSelectedItem().toString(), droplist2.getSelectedItem().toString(), api);
                }
            }
        });

        back = findViewById(R.id.back);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Calculations performed here
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Empty
    }
    public void fetchData(String type1, String type2, double amount){
        results = findViewById(R.id.balance);

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.currencyapi.com/v3/latest?apikey=xkIKiIuYuYFf82N95BoYnZgEUKwxE0ONPebZnsUD";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();
                    Convert.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run(){
                            try {
                                JSONObject jsonObject = new JSONObject(myResponse);
                                JSONObject data = jsonObject.getJSONObject("data");

                                if(!(array.size() <= 0)){
                                    JSONObject first = data.getJSONObject(type1);
                                    double from = Double.parseDouble(first.getString("value"));
                                    JSONObject second = data.getJSONObject(type2);
                                    double to = Double.parseDouble(second.getString("value"));
                                    results.setText(String.format("%.2f", amount * (to/from)));
                                }

                                for(int i = 0; i < data.length(); i++){
                                    adapter.add(data.names().getString(i));
                                }
                                droplist.setAdapter(adapter);
                                droplist2.setAdapter(adapter);
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}