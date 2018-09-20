package com.technard.waycalc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.technard.waycalc.R;

import java.util.ArrayList;

public class ActivityCalc extends AppCompatActivity implements View.OnClickListener{

    private EditText et;
    private TextView tvAdd, tvSub, tvEqual, tvDecimal, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv0;

    private ArrayList<Integer> nos = new ArrayList<>();
    private ArrayList<String> operations = new ArrayList<>();
    private int answer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        init();
    }

    private void init() {
        answer = 0;

        et = findViewById(R.id.et_calc);
        tvAdd = findViewById(R.id.b_add);
        tvSub = findViewById(R.id.b_sub);
        tvEqual = findViewById(R.id.b_equal);
        tvDecimal = findViewById(R.id.b_decimal);
        tv1 = findViewById(R.id.b_1);
        tv2 = findViewById(R.id.b_2);
        tv3 = findViewById(R.id.b_3);
        tv4 = findViewById(R.id.b_4);
        tv5 = findViewById(R.id.b_5);
        tv6 = findViewById(R.id.b_6);
        tv7 = findViewById(R.id.b_7);
        tv8 = findViewById(R.id.b_8);
        tv9 = findViewById(R.id.b_9);
        tv0 = findViewById(R.id.b_0);

        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvSub.setOnClickListener(this);
        tvEqual.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_1:
                insertNumber(1);
                break;
            case R.id.b_2:
                insertNumber(2);
                break;
            case R.id.b_3:
                insertNumber(3);
                break;
            case R.id.b_4:
                insertNumber(4);
                break;
            case R.id.b_5:
                insertNumber(5);
                break;
            case R.id.b_6:
                insertNumber(6);
                break;
            case R.id.b_7:
                insertNumber(7);
                break;
            case R.id.b_8:
                insertNumber(8);
                break;
            case R.id.b_9:
                insertNumber(9);
                break;
            case R.id.b_0:
                insertNumber(0);
                break;
            case R.id.b_add:
                insertOperation("+");
                break;
            case R.id.b_sub:
                insertOperation("-");
                break;
            case R.id.b_equal:
                calculate();
                break;
        }
    }

    private void calculate() {
        String curr = et.getText().toString();

        if(!curr.equals("+") || !curr.equals("-")){
            nos.add(Integer.valueOf(curr));
        }

        int sizeN = nos.size();
        int sizeO = operations.size();
        answer = nos.get(sizeN - 1);
        nos.remove(sizeN - 1);

        while(nos.size() > 0){
            sizeN = nos.size();
            sizeO = operations.size();

            int number = nos.get(sizeN - 1);
            String operation = operations.get(sizeO - 1);

            if(operation.equals("+"))
                answer += number;

            if(operation.equals("-"))
                answer -= (number * -1);

            nos.remove(sizeN - 1);
            operations.remove(sizeO - 1);

            Log.d("Calc", "calculate: " + answer);
        }

        et.setText("" + answer);
    }

    private void insertNumber(int digit){
        int curr = Integer.parseInt(et.getText().toString());

        if(curr == 0){
            et.setText("" + digit);
        } else {
            et.setText("" + curr + digit);
        }


    }

    private void insertOperation(String operation){
        int curr = Integer.parseInt(et.getText().toString());

        et.setText("" + curr + operation);

        nos.add(curr);
        operations.add(operation);

        et.setText("0");
    }

    private String getLastCharacter(){
        String curr = et.getText().toString();
        return curr.substring(curr.length()-2, curr.length()-1);
    }
}
