package com.example.user.project_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Fourth_dimension extends AppCompatActivity {
    String[] data = {"millisecond","second", "minute", "hour", "day", "week", "month with 30 days", "Non Leap year"};
    Map<String, Double> types = new TreeMap<>();
    static String from = "";
    static String to = "";
    static String input= "";
    static EditText in;
    static TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_dimension);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_f);
        final EditText in = (EditText)findViewById(R.id.editText_f);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        Button run = (Button)findViewById(R.id.run_f);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = (in.getText()).toString();
                Proverochka s = new Proverochka();
                if(s.check(input)==true){
                    (Toast.makeText(Fourth_dimension.this, "success", Toast.LENGTH_SHORT)).show();
                    Fourth_convetrer(from, to, input);}
                else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
            }
        });
    }
    void type_init(){
        types.put("millisecond", 0.001);
        types.put("second", 1.0);
        types.put("minute", 60.0);
        types.put("hour", 3600.0);
        types.put("day", 86400.0);
        types.put("week", 604800.0);
        types.put("month with 30 days", 2592000.0);
        types.put("Non Leap year", 31536000.0);
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_f);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = data[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_f);
        to_spinner.setAdapter(a);
        to_spinner.setPrompt("Select type");
        to_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = data[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void Fourth_convetrer(String from, String to, String amount){
        double from_amount = Double.parseDouble(amount);
        double to_amount = types.get(from)*from_amount/types.get(to);
        if(to_amount%1 == 0){int myInt = (int) to_amount;
            String output = Integer.toString(myInt);
            answer.setText(output);}
        else{
            String output = Double.toString(to_amount);
            answer.setText(output);}
    }}
