package com.example.exercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView result;
    private EditText v1, v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textViewResult);
        v1 = findViewById(R.id.editTextValue1);
        v2 = findViewById(R.id.editTextValue2);

        Button buttons = findViewById(R.id.buttonRun);
        buttons.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int selectedOperationId = ((RadioGroup) findViewById(
                R.id.radioGroupOptions)).getCheckedRadioButtonId();

        float value1 = Float.parseFloat(v1.getText().toString());
        float value2 = Float.parseFloat(v2.getText().toString());

        if (selectedOperationId == R.id.radioSum) {
            result.setText(MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 + value2));
        }
        if (selectedOperationId == R.id.radioMinus) {
            result.setText(MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 - value2));
        }
        if (selectedOperationId == R.id.radioMutiplier) {
            result.setText(MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 * value2));
        }
        if (selectedOperationId == R.id.radioDivider) {
            result.setText(MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 / value2));
        }
    }
}