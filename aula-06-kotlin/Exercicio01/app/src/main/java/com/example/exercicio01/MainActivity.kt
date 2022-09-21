package com.example.exercicio01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercicio01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val v1 = binding.editTextValue1
        val v2 = binding.editTextValue2

        val btnSum = binding.buttonSum
        btnSum.setOnClickListener {
            val resultado = v1.text.toString().toFloat() + v2.text.toString().toFloat()
            binding.textViewResult.text = getString(R.string.resultado, resultado.toString())
        }

        val btnMinus = binding.buttonMinus
        btnMinus.setOnClickListener { val resultado = v1.text.toString().toFloat() - v2.text.toString().toFloat()
            binding.textViewResult.text = getString(R.string.resultado, resultado.toString()) }

        val btnMultiplier = binding.buttonMultiplier
        btnMultiplier.setOnClickListener {val resultado = v1.text.toString().toFloat() * v2.text.toString().toFloat()
            binding.textViewResult.text = getString(R.string.resultado, resultado.toString())}

        val btnDivider = binding.buttonDivider
        btnDivider.setOnClickListener { val resultado = v1.text.toString().toFloat() / v2.text.toString().toFloat()
            binding.textViewResult.text = getString(R.string.resultado, resultado.toString()) }
    }
}