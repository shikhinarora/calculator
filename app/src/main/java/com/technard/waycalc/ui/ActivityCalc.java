package com.technard.waycalc.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.technard.waycalc.R;

import java.util.ArrayList;

public class ActivityCalc extends AppCompatActivity implements View.OnClickListener{

    private EditText et;
    private TextView tvAdd, tvSub, tvMul, tvDivide, tvEqual, tvDecimal, tvDel, tv1, tv2, tv3, tv4,
            tv5, tv6, tv7, tv8, tv9, tv0;
    private LinearLayout llMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        init();
    }

    private void init() {
        et = findViewById(R.id.et_calc);
        tvAdd = findViewById(R.id.b_add);
        tvSub = findViewById(R.id.b_sub);
        tvMul = findViewById(R.id.b_mul);
        tvDivide = findViewById(R.id.b_divide);
        tvEqual = findViewById(R.id.b_equal);
        tvDecimal = findViewById(R.id.b_decimal);
        tvDel = findViewById(R.id.b_del);
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
        llMain = findViewById(R.id.ll_main);

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
        tvMul.setOnClickListener(this);
        tvDivide.setOnClickListener(this);
        tvDecimal.setOnClickListener(this);
        tvDel.setOnClickListener(this);
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
            case R.id.b_mul:
                insertOperation("*");
                break;
            case R.id.b_divide:
                insertOperation("/");
                break;
            case R.id.b_decimal:
                insertDecimal();
                break;
            case R.id.b_equal:
                calculate();
                break;
            case R.id.b_del:
                delete();
                break;
            default:
                break;
        }
    }

    private void delete() {
        String curr = et.getText().toString();
        int currLen = curr.length();

        if (currLen <= 1){
            et.setText("0");
        } else {
            et.setText(curr.substring(0, currLen-1));
        }
    }

    private void calculate(){
        String expression = et.getText().toString();
        int len = expression.length();

        if(len > 0){
            String firstChar = expression.substring(0, 1);
            if (isOperator(firstChar)){
                if(firstChar.equals("-")){
                    expression = "0" + expression;
                    len++;
                } else {
                    expression = expression.substring(1, len);
                    len--;
                }
            }

            String nOne = parseExpression(expression, len);

            if (nOne.equals(""))
                nOne = "0";

            String[] tempAns = nOne.split("\\.");

            if(tempAns.length > 1){
                int dec = Integer.parseInt(tempAns[1]);
                if(dec != 0 || expression.contains(".")) {
                    double answer = Double.parseDouble(nOne);
                    et.setText("" + answer);
                } else{
                    nOne = tempAns[0];
                    int answer = Integer.parseInt(nOne);
                    et.setText("" + answer);
                }
            } else {
                int answer = Integer.parseInt(nOne);
                et.setText("" + answer);
            }
        } else {
            et.setText("0");
        }
    }

    @NonNull
    private String parseExpression(String expression, int len) {
        String nOne = "", nTwo = "";
        String operator = "";

        for(int i = 0; i < len; i++){
            String chr = String.valueOf(expression.charAt(i));

            if (isOperator(chr)){
                if (operator.equals(""))
                    operator = chr;
                else{
                    double n1 = Double.parseDouble(nOne);
                    double n2 = Double.parseDouble(nTwo);

                    nOne = String.valueOf(useOperator(n1, n2, operator));
                    nTwo = "";
                    operator = chr;
                }
            } else {
                if(nOne.equals(""))
                    nOne = nOne + chr;
                else{
                    if (operator.equals(""))
                        nOne = nOne + chr;
                    else
                        nTwo = nTwo + chr;
                }
            }

            if (i == len - 1){
                if(!nTwo.equals("") && !operator.equals("")){
                    double n1 = Double.parseDouble(nOne);
                    double n2 = Double.parseDouble(nTwo);

                    nOne = String.valueOf(useOperator(n1, n2, operator));
                }
            }
        }
        return nOne;
    }

    private boolean isOperator(String chr){
        if (chr.equals("+") || chr.equals("-") || chr.equals("*") || chr.equals("/"))
            return true;
        else
            return false;
    }

    private double useOperator(double one, double two, String operator){
        double answer = 0;

        if(operator.equals("+"))
            answer = one + two;

        if(operator.equals("-"))
            answer = one - two;

        if(operator.equals("*"))
            answer = one * two;

        if(operator.equals("/")){
            if(two == 0){
                Snackbar.make(llMain, "Canot divide by zero", Snackbar.LENGTH_SHORT).show();
            } else
                answer = one / two;
        }

        return answer;
    }

    private void insertNumber(int digit){
        String curr = et.getText().toString();

        if(curr.equals("0")){
            et.setText("" + digit);
        } else {
            et.setText("" + curr + digit);
        }
    }

    private void insertOperation(String operation){
        String curr = et.getText().toString();
        int currLen = curr.length();

        if(currLen == 1 && isOperator(curr))
            et.setText(curr + operation);
        else{
            if (isOperator(curr.substring(currLen-1, currLen)))
                et.setText(curr.substring(0, currLen-1) + operation);
            else
                et.setText(curr + operation);
        }
    }

    private void insertDecimal(){
        String curr = et.getText().toString();
        int currLen = curr.length();

        if(!curr.substring(currLen-1, currLen).equals(".") && !isOperator(curr.substring(currLen-1, currLen)))
            et.setText(curr + ".");
    }
}
