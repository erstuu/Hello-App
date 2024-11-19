package com.erstuu.uts.model

data class UserModel(
    var name: String,
    var email: String,
    var age: Int,

) {
    fun getUserInfo(): String {
        return "Name: $name\nEmail: $email\nAge: $age"
    }
}