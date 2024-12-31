package com.example.simuladorbanco

import java.io.Serializable

data class User(val nombre: String, val apellido: String, val usuario: String, val contrasena: String, var saldo: Double, val numeroCuenta: Int) : Serializable
