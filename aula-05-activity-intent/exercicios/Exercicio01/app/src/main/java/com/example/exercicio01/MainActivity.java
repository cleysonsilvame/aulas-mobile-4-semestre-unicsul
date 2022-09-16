package com.example.exercicio01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    private TextView result;
    private EditText altura, peso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textViewResult);
        altura = findViewById(R.id.editTextAltura);
        peso = findViewById(R.id.editTextPeso);

        Button btnCalcularIMC = findViewById(R.id.buttonCalcularIMC);
        btnCalcularIMC.setOnClickListener(this);

        Button btnAbrirCalculadora = findViewById(R.id.buttonAbrirCalculadora);
        btnAbrirCalculadora.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonCalcularIMC) {
            float alturaValue = Float.parseFloat(altura.getText().toString());
            float pesoValue = Float.parseFloat(peso.getText().toString());


            float imc = pesoValue / (alturaValue * alturaValue);

            Log.d("cley", Float.toString(pesoValue / (alturaValue * alturaValue)));


            if (imc < 18.5)
                result.setText(MessageFormat.format("IMC: {0}, é considerado MAGREZA!", imc));
            else if (imc < 24.9)
                result.setText(MessageFormat.format("IMC: {0}, é considerado NORMAL!", imc));
            else if (imc < 29.9)
                result.setText(MessageFormat.format("IMC: {0}, é considerado SOBREPESO!", imc));
            else if (imc < 39.9)
                result.setText(MessageFormat.format("IMC: {0}, é considerado OBESIDADE!", imc));
            else if (imc >= 40)
                result.setText(MessageFormat.format("IMC: {0}, é considerado OBESIDADE GRAVE!", imc));
            else
                result.setText(R.string.errorMessage);
        }

        if (v.getId() == R.id.buttonAbrirCalculadora) {
            Intent intent = new Intent(this, CalculadoraActivity.class);
            startActivity(intent);
        }

    }

}