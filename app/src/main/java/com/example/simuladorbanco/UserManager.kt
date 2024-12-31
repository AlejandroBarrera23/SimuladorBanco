package com.example.simuladorbanco

object UserManager {
    val usuarios = mutableListOf<User>()

    fun createUser(nombre: String, apellido: String, usuario: String, contrasena: String, saldo: Double, numeroCuenta: Int): Boolean {
        if (usuarios.any { it.usuario == usuario }) return false
        usuarios.add(User(nombre, apellido, usuario, contrasena, saldo, numeroCuenta))
        return true
    }

    fun updateUser(updatedUser: User) {
        val index = usuarios.indexOfFirst { it.numeroCuenta == updatedUser.numeroCuenta }
        if (index != -1) {
            usuarios[index] = updatedUser
        }
    }

    fun login(usuario: String, contrasena: String): Boolean {
        return usuarios.any { it.usuario == usuario && it.contrasena == contrasena }
    }
}