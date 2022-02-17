package es.tipolisto.breeds.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    var retrofit:Retrofit?=null
    private fun createRetrofit(baseUrl:String?): Retrofit? {
        if(retrofit==null){
            retrofit=Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}