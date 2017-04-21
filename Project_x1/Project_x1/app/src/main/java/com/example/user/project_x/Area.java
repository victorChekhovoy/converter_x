package com.example.user.project_x;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.TreeMap;



public class Area extends AppCompatActivity{
    String[] data = {"square kilometers","square meters","square centimeters","square millimeters","miles","inches","yards","feets","hectars","ares"};
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
        answer.setText("result");
        final EditText in = (EditText)findViewById(R.id.editText_b);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                input = (in.getText()).toString();
                Check a = new Check();
                if(a.check(input) && input.length()>=1){
                    if(input.equals(".") && input.length()<2){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else{Area_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}}
        });
}
    @Override
    protected void onResume() {
        answer.setText("");
        super.onResume();
    }


    void type_init(){
        types.put("square kilometers", 1000000.0);
        types.put("square meters", 1.0);
        types.put("square centimeters", 0.0001);
        types.put("square millimeters", 0.000001);
        types.put("miles", 2589988.11);
        types.put("inches", 0.00064516);
        types.put("yards", 0.83612736);
        types.put("feets", 0.09290304);
        types.put("hectars", 10000.0);
        types.put("ares", 100.0);
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_b);
        from_spinner.setAdapter(a);
        from_spinner.setPrompt("Select type");
        from_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = data[position];
                Check a = new Check();
                if(a.check(input) && input.length()>=1){
                    if(input.equals(".") && input.length()<2){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else{Area_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}

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
                Check a = new Check();
                if(a.check(input) && input.length()>=1){
                    if(input.equals(".") && input.length()<2){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else{Area_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void Area_convetrer(String from, String to, String amount){
        double from_amount = Double.parseDouble(amount);
        double to_amount = types.get(from)*from_amount/types.get(to);
        if(to_amount%1 == 0){long myInt = (long) to_amount;
            String output = Long.toString(myInt);
            answer.setText(output);}
        else{
            String output = Double.toString(to_amount);
            answer.setText(output);}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_1:
                finish();
                return true;
            case R.id.action_2:
                Intent intent1 = new Intent(this, Authors.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }}




