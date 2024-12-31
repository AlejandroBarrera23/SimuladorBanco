package com.example.simuladorbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val nombreEditText: EditText = findViewById(R.id.editTextNombre)
        val apellidoEditText: EditText = findViewById(R.id.editTextApellido)
        val usuarioEditText: EditText = findViewById(R.id.editTextUsuario)
        val contrasenaEditText: EditText = findViewById(R.id.editTextContrasena)
        val confirmarContrasenaEditText: EditText = findViewById(R.id.editTextConfirmarContrasena)
        val saldoEditText: EditText = findViewById(R.id.editTextSaldo)
        val crearCuentaButton: Button = findViewById(R.id.buttonCrearCuenta)
        val regresarLoginButton: Button = findViewById(R.id.buttonRegresarLogin)

        crearCuentaButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val apellido = apellidoEditText.text.toString()
            val usuario = usuarioEditText.text.toString()
            val contrasena = contrasenaEditText.text.toString()
            val confirmarContrasena = confirmarContrasenaEditText.text.toString()
            val saldo = saldoEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (contrasena == confirmarContrasena) {
                val numeroCuenta = (100000..999999).random()
                if (UserManager.createUser(nombre, apellido, usuario, contrasena, saldo, numeroCuenta)) {
                    Toast.makeText(this, "Cuenta creada exitosamente. Número de cuenta: $numeroCuenta", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }

        regresarLoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}