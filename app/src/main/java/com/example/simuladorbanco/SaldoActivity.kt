package com.example.simuladorbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SaldoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saldo)

        val saldoTextView: TextView = findViewById(R.id.textViewSaldo)
        val regresarButton: Button = findViewById(R.id.buttonRegresarMenu)

        val saldo = intent.getDoubleExtra("saldo", 0.0)
        saldoTextView.text = "Tu saldo actual es: $saldo"

        regresarButton.setOnClickListener {
            finish()
        }
    }
}