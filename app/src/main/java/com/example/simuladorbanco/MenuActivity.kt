package com.example.simuladorbanco

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val bienvenidaTextView: TextView = findViewById(R.id.textViewBienvenida)
        val numeroCuentaTextView: TextView = findViewById(R.id.textViewNumeroCuenta)
        val consultaSaldoButton: Button = findViewById(R.id.buttonConsultaSaldo)
        val depositoButton: Button = findViewById(R.id.buttonDeposito)
        val retiroButton: Button = findViewById(R.id.buttonRetiro)
        val transferenciaButton: Button = findViewById(R.id.buttonTransferencia)
        val cerrarSesionButton: Button = findViewById(R.id.buttonCerrarSesion)

        user = intent.getSerializableExtra("usuario") as User

        bienvenidaTextView.text = "Bienvenido, ${user.nombre} ${user.apellido}"
        numeroCuentaTextView.text = "Número de cuenta: ${user.numeroCuenta}"

        consultaSaldoButton.setOnClickListener {
            val intent = Intent(this, SaldoActivity::class.java)
            intent.putExtra("saldo", user.saldo)
            startActivity(intent)
        }

        depositoButton.setOnClickListener {
            val intent = Intent(this, DepositoActivity::class.java)
            intent.putExtra("usuario", user)
            startActivityForResult(intent, 1)
        }

        retiroButton.setOnClickListener {
            val intent = Intent(this, RetiroActivity::class.java)
            intent.putExtra("usuario", user)
            startActivityForResult(intent, 1)
        }

        transferenciaButton.setOnClickListener {
            val intent = Intent(this, TransferenciaActivity::class.java)
            intent.putExtra("usuario", user)
            startActivityForResult(intent, 1)
        }

        cerrarSesionButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val updatedUser = data?.getSerializableExtra("usuario") as? User
            if (updatedUser != null) {
                user = updatedUser
                val bienvenidaTextView: TextView = findViewById(R.id.textViewBienvenida)
                val numeroCuentaTextView: TextView = findViewById(R.id.textViewNumeroCuenta)
                bienvenidaTextView.text = "Bienvenido, ${user.nombre} ${user.apellido}"
                numeroCuentaTextView.text = "Número de cuenta: ${user.numeroCuenta}"
            }
        }
    }
}