package com.example.fetch_androidinternshipcodingexercise
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface APIService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}

/*
    Convert HTTP API into a callable interface with Retrofit
    The implementation uses GSON to convert the JSON into an object
*/
object RetrofitInstance {
    // lazy ensures that the Retrofit object is only instantiated once
    val api: APIService by lazy {
        Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}
