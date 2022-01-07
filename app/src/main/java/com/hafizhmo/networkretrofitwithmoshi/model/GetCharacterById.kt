package com.hafizhmo.networkretrofitwithmoshi.model

data class GetCharacterById(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)