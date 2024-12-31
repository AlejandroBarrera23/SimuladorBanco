package com.example.simuladorbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransferenciaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transferencia)

        val cuentaDestinoEditText: EditText = findViewById(R.id.editTextCuentaDestino)
        val montoEditText: EditText = findViewById(R.id.editTextMontoTransferencia)
        val transferirButton: Button = findViewById(R.id.buttonTransferir)
        val regresarButton: Button = findViewById(R.id.buttonRegresarMenu)

        val user = intent.getSerializableExtra("usuario") as User

        transferirButton.setOnClickListener {
            val cuentaDestinoStr = cuentaDestinoEditText.text.toString().trim()
            val montoStr = montoEditText.text.toString().trim()

            if (cuentaDestinoStr.isEmpty() || montoStr.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cuentaDestino = cuentaDestinoStr.toIntOrNull()
            val monto = montoStr.toDoubleOrNull()

            if (cuentaDestino == null || monto == null || monto <= 0 || monto > user.saldo) {
                Toast.makeText(this, "Datos inválidos o saldo insuficiente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val receptor = UserManager.usuarios.find { it.numeroCuenta == cuentaDestino }
            if (receptor == null) {
                Toast.makeText(this, "Número de cuenta no encontrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            user.saldo -= monto
            receptor.saldo += monto
            UserManager.updateUser(user)
            UserManager.updateUser(receptor)
            Toast.makeText(this, "Transferencia realizada con éxito", Toast.LENGTH_SHORT).show()
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