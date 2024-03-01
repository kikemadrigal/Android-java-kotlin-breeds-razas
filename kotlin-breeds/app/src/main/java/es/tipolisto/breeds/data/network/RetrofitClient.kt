package es.tipolisto.breeds.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import es.tipolisto.breeds.data.models.dog.Dog
import es.tipolisto.breeds.data.models.fish.Fish
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory


class RetrofitClient{
    companion object{
        fun createRetrofit(baseUrl:String, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        // .addConverterFactory(ScalarsConverterFactory.create())
        fun getRetrofitCatService():ICatApiService{
            val gson = GsonBuilder()
                .create()
            val retrofit=createRetrofit("https://api.thecatapi.com/v1/",gson)
            return retrofit.create(ICatApiService::class.java)
        }
        fun getRetroDogService():IDogApiService{
            val gson = GsonBuilder()
                .registerTypeAdapter(Dog::class.java, DogDeserializer())
                .create()
            val retrofit=createRetrofit("https://api.thedogapi.com/v1/", gson)
            return retrofit.create(IDogApiService::class.java)
        }
        fun getRetrofitFishService():IFishApiService{
            val gson = GsonBuilder()
                .registerTypeAdapter(Fish::class.java, FishDeserializer())
                .create()
            val retrofit=createRetrofit("https://fish-species.p.rapidapi.com/fish_api/", gson)
            return retrofit.create(IFishApiService::class.java)
        }
    }


}
