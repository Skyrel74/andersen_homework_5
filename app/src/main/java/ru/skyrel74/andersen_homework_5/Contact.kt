package ru.skyrel74.andersen_homework_5

import java.io.Serializable

data class Contact(
    val id: Int,
    var name: String,
    var surname: String,
    var phone: String
) : Serializable