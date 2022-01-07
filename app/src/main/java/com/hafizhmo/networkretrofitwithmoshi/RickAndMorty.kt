package com.hafizhmo.networkretrofitwithmoshi

import com.hafizhmo.networkretrofitwithmoshi.model.GetCharacterById
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMorty {

    @GET("character/2")
    fun getCharacterById(): Call<GetCharacterById>
}
