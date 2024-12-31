package com.example.simuladorbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RetiroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retiro)

        val montoEditText: EditText = findViewById(R.id.editTextMontoRetiro)
        val retirarButton: Button = findViewById(R.id.buttonRetirar)
        val regresarButton: Button = findViewById(R.id.buttonRegresarMenu)

        val user = intent.getSerializableExtra("usuario") as User

        retirarButton.setOnClickListener {
            val montoStr = montoEditText.text.toString().trim()
            if (montoStr.isEmpty()) {
                Toast.makeText(this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val monto = montoStr.toDoubleOrNull()
            if (monto == null || monto <= 0 || monto > user.saldo) {
                Toast.makeText(this, "Monto inválido o saldo insuficiente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            user.saldo -= monto
            UserManager.updateUser(user)
            Toast.makeText(this, "Retiro realizado con éxito", Toast.LENGTH_SHORT).show()
            val resultIntent = Intent()
            resultIntent.putExtra("usuario", user)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        regresarButton.setOnClickListener {
            finish()
        }
    }
}