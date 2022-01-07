package com.hafizhmo.networkretrofitwithmoshi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.hafizhmo.networkretrofitwithmoshi.model.GetCharacterById
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.name)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAndMortyService = retrofit.create(RickAndMorty::class.java)

        rickAndMortyService.getCharacterById().enqueue(object : Callback<GetCharacterById> {
            override fun onResponse(
                call: Call<GetCharacterById>,
                response: Response<GetCharacterById>
            ) {
                Log.i("MainActivity", response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Failed network request!", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                val body = response.body()!!
                val name = body.name

                textView.text = name
            }

            override fun onFailure(call: Call<GetCharacterById>, t: Throwable) {
                Log.i("MainActivity", t.toString())
            }

        })
    }
}