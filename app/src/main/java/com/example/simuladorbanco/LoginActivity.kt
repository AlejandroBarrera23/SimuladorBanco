package com.example.simuladorbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usuarioEditText: EditText = findViewById(R.id.editTextUsuario)
        val contrasenaEditText: EditText = findViewById(R.id.editTextContrasena)
        val loginButton: Button = findViewById(R.id.buttonLogin)
        val crearCuentaButton: Button = findViewById(R.id.buttonCrearCuenta)

        loginButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString().trim()
            val contrasena = contrasenaEditText.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = UserManager.usuarios.find { it.usuario == usuario && it.contrasena == contrasena }
            if (user != null) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("usuario", user)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        crearCuentaButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
