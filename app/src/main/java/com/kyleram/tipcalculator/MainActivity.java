package com.kyleram.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button calcButton;
    private EditText mealTotal;
    private EditText tipAmount;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonOnClickListener();
    }

    private void setupButtonOnClickListener() {
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mealTotal.getText().toString().isEmpty()||tipAmount.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please input the meal total and the tip amount...", Toast.LENGTH_LONG).show();
                } else {
                    double resultAmt = calculateTip();
                    displayResult(resultAmt);
                }
            }
        });
    }

    private void displayResult(double resultAmt) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.00");
        String amtString = decimalFormatter.format(resultAmt);
        String tipString = tipAmount.getText().toString();
        double tipAmt = Double.parseDouble(tipAmount.getText().toString())/100*Double.parseDouble(mealTotal.getText().toString());
        String tipAmtString = decimalFormatter.format(tipAmt);

        resultText.setText("With a " + tipString + "% tip, the total is now: $" + amtString + " - The tip you are giving the server is $" + tipAmtString);
    }

    private double calculateTip() {
        String totalString = mealTotal.getText().toString();
        String tipString = tipAmount.getText().toString();

        double total = Double.parseDouble(totalString);
        double tipPercent = (Double.parseDouble(tipString))/100;

        double tip = tipPercent *total;

        return total + tip;
    }


    private void findViews(){
        calcButton = findViewById(R.id.calc_button);
        mealTotal = findViewById(R.id.meal_total);
        tipAmount = findViewById(R.id.tip_percentage);
        resultText = findViewById(R.id.result_total);
    }
}