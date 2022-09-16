package com.example.exercicio01;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class CalculadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);


        EditText v1 = findViewById(R.id.editTextValue1);
        EditText v2 = findViewById(R.id.editTextValue2);
        TextView r = findViewById(R.id.textViewResult);


        Button btnSum = findViewById(R.id.buttonSum);
        btnSum.setOnClickListener(v -> r.setText(MessageFormat.format("{0} {1}", getString(R.string.resultadoIMC), (Float.parseFloat(v1.getText().toString()) + Float.parseFloat(v2.getText().toString())))));

        Button btnMinus = findViewById(R.id.buttonMinus);
        btnMinus.setOnClickListener(v -> r.setText(MessageFormat.format("{0} {1}", getString(R.string.resultadoIMC), Float.parseFloat(v1.getText().toString()) - Float.parseFloat(v2.getText().toString()))));

        Button btnMultiplier = findViewById(R.id.buttonMultiplier);
        btnMultiplier.setOnClickListener(v -> r.setText(MessageFormat.format("{0} {1}", getString(R.string.resultadoIMC), Float.parseFloat(v1.getText().toString()) * Float.parseFloat(v2.getText().toString()))));

        Button btnDivider = findViewById(R.id.buttonDivider);
        btnDivider.setOnClickListener(v -> r.setText(MessageFormat.format("{0} {1}", getString(R.string.resultadoIMC), Float.parseFloat(v1.getText().toString()) / Float.parseFloat(v2.getText().toString()))));

        Button btnVoltar = findViewById(R.id.buttonVoltar);
        btnVoltar.setOnClickListener(v -> finish());
    }
}