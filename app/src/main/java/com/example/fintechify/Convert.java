package com.example.fintechify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

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
    private double api;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        droplist = findViewById(R.id.droplist);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        droplist.setAdapter(adapter);
        droplist.setOnItemSelectedListener(this);

        droplist2 = findViewById(R.id.droplist2);
        droplist2.setAdapter(adapter);
        droplist2.setOnItemSelectedListener(this);

        inputAmount = (EditText) findViewById(R.id.inputAmount);
        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               try {
                   api = Double.parseDouble(inputAmount.getText().toString());
               } catch (Exception e){
                   api = 1.00;
               }
               fetchData(droplist.getSelectedItem().toString(), droplist2.getSelectedItem().toString(), api);
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
        results = findViewById(R.id.results);

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
                                JSONObject first = data.getJSONObject(type1);
                                double from = Double.parseDouble(first.getString("value"));
                                JSONObject second = data.getJSONObject(type2);
                                double to = Double.parseDouble(second.getString("value"));
                                results.setText(String.format("%.2f", amount * (to/from)));

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