package com.example.exercicio2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.exercicio2.databinding.ActivityMainBinding
import java.text.MessageFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRun.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val selectedOperationId = binding.radioGroupOptions.checkedRadioButtonId
        val value1 = binding.editTextValue1.text.toString().toFloat()
        val value2 = binding.editTextValue2.text.toString().toFloat()


        if (selectedOperationId == binding.radioSum.id) {
            binding.textViewResult.text =
                MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 + value2)
            return
        }
        if (selectedOperationId == binding.radioMinus.id) {
            binding.textViewResult.text =
                MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 - value2)
            return
        }
        if (selectedOperationId == binding.radioMutiplier.id) {
            binding.textViewResult.text =
                MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 * value2)
            return
        }
        if (selectedOperationId == binding.radioDivider.id) {
            binding.textViewResult.text =
                MessageFormat.format("{0} {1}", getString(R.string.resultText), value1 / value2)
            return
        }
    }
}