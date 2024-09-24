package com.example.calculator_java;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Double first_num;
    private Double second_num;
    private Boolean isOperationClick, isPlus, isMinus, isMultiply, isDivision;
    private Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_View);

    }

    public void onNumberClick(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");
            first_num = (double) 0;
            second_num = (double) 0;
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(text);
        } else {
            textView.append(text);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        int id = view.getId();
        first_num = Double.parseDouble(textView.getText().toString().replace(",", "."));
        if (id == R.id.Plus) {
            isPlus = true;
        } else if (id == R.id.Minus) {
            isMinus = true;
        } else if (id == R.id.Multiply) {
            isMultiply = true;

        } else if (id == R.id.Division) {
            isDivision = true;
        }
        isOperationClick = true;
    }

    public void onEqualClick(View view) {
        second_num = Double.parseDouble((textView.getText().toString().replace(",", ".")));
        if (isPlus) {
            result = first_num +second_num;
            isPlus = false;
        } else if (isMinus) {
            result = first_num - second_num;
            isMinus = false;
        } else if (isMultiply) {
            result = first_num * second_num;
            isMultiply = false;
        } else {
            result = first_num /second_num;
            isDivision = false;
        }
        if (second_num != 0){
            if (result % 1 == 0){
              int  result_int = result.intValue();
              textView.setText(String.valueOf(result_int));

            } else {
                textView.setText(String.valueOf(result).replace(".",","));
            }
        }else {
            textView.setText("ERR");
        }
        first_num = result;
        second_num = (double) 0;


    }
    public void onPlusMinusCLick(View view){
        if (textView.getText().toString().contains("-")){
            textView.setText(textView.toString().replace("-",""));
        }else {
            textView.setText("-"+ textView.getText());
        }
    }
    public void onPercentClick(View view){
        result = Double.parseDouble(textView.getText().toString())/ 100;
        if (result % 1 == 0){
            int  result_int = result.intValue();
            textView.setText(String.valueOf(result_int));

        } else {
            textView.setText(String.valueOf(result).replace(".",","));
        }
    }
}