package com.example.user.project_x;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class Volume extends AppCompatActivity {
    String[] data = {"milliliters","teaspoons", "liters", "cubic meters"};
    Map<String, Double> types = new TreeMap<>();
    static String from = "";
    static String to = "";
    static String input= "";
    static EditText in;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        type_init();
        answer = (TextView)findViewById(R.id.textView2_v);
        final EditText in = (EditText)findViewById(R.id.editText_v);
        spinner_from_init(adapter);
        spinner_to_init(adapter);
        in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                input = (in.getText()).toString();
                Check a = new Check();
                if(a.check(input) && input.length()>=1){
                    if(input.equals(".") && input.length()<2){
                        Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();
                    }
                    else{Volume_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                else{answer.setText("result");}}
            }
        });}

    void type_init(){
       types.put("milliliters", 1.0);
     types.put("teaspoons", 5.0);
       types.put("liters", 1000.0);
    types.put("cubic meters", 1000000.0);
    }
    void spinner_from_init(ArrayAdapter a){
        Spinner from_spinner = (Spinner) findViewById(R.id.from_type_v);
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
                    else{Volume_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });}
    void spinner_to_init(ArrayAdapter a){
        Spinner to_spinner = (Spinner)findViewById(R.id.spinner_v);
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
                    else{Volume_convetrer(from, to, input);}}
                else{
                    if(a.check(input)==false && input.length()>=1){Toast.makeText(getApplicationContext(), "bad symbols", Toast.LENGTH_SHORT).show();}
                    else{answer.setText("result");}}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    void Volume_convetrer(String from, String to, String amount){
        double from_amount = Double.parseDouble(amount);
        double to_amount = types.get(from)*from_amount/types.get(to);
        if(to_amount%1 == 0){int myInt = (int) to_amount;
            String output = Integer.toString(myInt);
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
