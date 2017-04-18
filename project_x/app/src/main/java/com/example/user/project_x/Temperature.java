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

public class Temperature extends AppCompatActivity {
    String[] data = {"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur"};
    Map<String, String> types = new TreeMap<>();
    static String from = "";
    static String to = "";
    static String input= "";
    static EditText in;
    static TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_t);
        final EditText in = (EditText)findViewById(R.id.editText_t);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        Button run = (Button)findViewById(R.id.run_t);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = (in.getText()).toString();
                Proverochka s = new Proverochka();
                if(s.check(input)==true){
                    (Toast.makeText(Temperature.this, "success", Toast.LENGTH_SHORT)).show();
                    Temp_convetrer(from, to, input);}
                else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
            }
        });
    }


    void type_init(){
        types.put("Celsius", "c");
        types.put("Fahrenheit", "f");
        types.put("Kelvin", "k");
        types.put("Rankine", "r");
        types.put("Reaumur", "re");
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_t);
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
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_t);
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

    double stringToInt(String s ){
        int result = 0;
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(0) == '-'){
                result -= s.charAt(i + 1);
            }
            else{
                result += s.charAt(i);
            }
        }
        return result;
    }
    void Temp_convetrer(String from, String to, String amount) {
        double from_amount = stringToInt(amount);
        double to_amount = converter(to, from, from_amount);
        if(to_amount%1 == 0){int myInt = (int) to_amount;
            String output = Integer.toString(myInt);
            answer.setText(output);}
        else{
            String output = Double.toString(to_amount);
            answer.setText(output);}
    }
    double converter(String to, String from, double a){
        switch (from){
            case "c":
                switch(to){
                    case "c": return a;
                    case "f": return a*1.8 +32;
                    case "k": return a +273.15;
                    case "r": return (a+273.15)*1.8;
                    case "re": return a*0.8;
                }
            case "f":
                switch(to){
                    case "c": return (a - 32)/1.8;
                    case "f": return a;
                    case "k": return (a + 459.67)/1.8;
                    case "r": return a + 459.67;
                    case "re": return (a - 32)/2.25;
                }
            case "k":
                switch(to){
                    case "c": return a - 273.15;
                    case "f": return a*1.8 - 459.67;
                    case "k": return a;
                    case "r": return a*1.8;
                    case "re": return (a - 273.15)*0.8;
                }
            case "r":
                switch(to){
                    case "c": return (a - 491.67)/1.8;
                    case "f": return a - 459.67;
                    case "k": return a/1.8;
                    case "r": return a;
                    case "re": return (a - 491.67)/2.25;
                }
            case "re":
                switch(to){
                    case "c": return a*1.25;
                    case "f": return a*2.25+32;
                    case "k": return a*1.25 + 273.15;
                    case "r": return a*2.25 +491.67;
                    case "re": return a;

                }                    }
        return a;
    }}

