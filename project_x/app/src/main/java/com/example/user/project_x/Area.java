package com.example.user.project_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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



public class Area extends AppCompatActivity{
    String[] data = {"square kilometer","square meter","square centimeter","square millimeter","mile","inch","yard","feet","hectar","are"};
    Map<String, Double> types = new TreeMap<>();
    static String from = "";
    static String to = "";
    static String input= "";
    static EditText in;
    static TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_b);
        final EditText in = (EditText)findViewById(R.id.editText_b);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        Button run = (Button)findViewById(R.id.run_b);
        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = (in.getText()).toString();
                Proverochka s = new Proverochka();
                if(s.check(input)==true){
                    (Toast.makeText(Area.this, "success", Toast.LENGTH_SHORT)).show();
                    Area_convetrer(from, to, input);}
                else{Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
            }
        });
    }
    void type_init(){
        types.put("square kilometer", 1000000.0);
        types.put("square meter", 1.0);
        types.put("square centimeter", 0.0001);
        types.put("square millimeter", 0.000001);
        types.put("mile", 2589988.11);
        types.put("inch", 0.00064516);
        types.put("yard", 0.83612736);
        types.put("feet", 0.09290304);
        types.put("hectar", 10000.0);
        types.put("are", 100.0);
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_b);
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
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_b);
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
    void Area_convetrer(String from, String to, String amount){
        double from_amount = Double.parseDouble(amount);
        double to_amount = types.get(from)*from_amount/types.get(to);
        if(to_amount%1 == 0){int myInt = (int) to_amount;
            String output = Integer.toString(myInt);
            answer.setText(output);}
        else{
            String output = Double.toString(to_amount);
            answer.setText(output);}
    }}




