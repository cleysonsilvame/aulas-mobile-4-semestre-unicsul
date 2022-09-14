package com.example.exercicio01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText v1 = findViewById(R.id.editTextValue1);
        EditText v2 = findViewById(R.id.editTextValue2);
        TextView r = findViewById(R.id.textViewResult);


        Button btnSum = findViewById(R.id.buttonSum);
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setText("Resultado: " + (Float.parseFloat(v1.getText().toString()) + Float.parseFloat(v2.getText().toString())));
            }
        });

        Button btnMinus = findViewById(R.id.buttonMinus);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setText("Resultado: " + (Float.parseFloat(v1.getText().toString()) - Float.parseFloat(v2.getText().toString())));
            }
        });

        Button btnMultiplier =  findViewById(R.id.buttonMultiplier);
        btnMultiplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setText("Resultado: " + (Float.parseFloat(v1.getText().toString()) * Float.parseFloat(v2.getText().toString())));
            }
        });

        Button btnDivider = findViewById(R.id.buttonDivider);
        btnDivider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r.setText("Resultado: " + (Float.parseFloat(v1.getText().toString()) / Float.parseFloat(v2.getText().toString())));
            }
        });
    }
}