package com.example.exemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Sobre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        TextView sobre = (TextView)findViewById(R.id.textViewSobre);

        Intent intent = getIntent();

        sobre.setText(intent.getStringExtra("sobre"));
    }
}