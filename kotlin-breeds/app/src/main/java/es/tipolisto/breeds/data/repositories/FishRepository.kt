package es.tipolisto.breeds.data.repositories

import android.util.Log
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.network.RetrofitClient
import es.tipolisto.breeds.data.providers.FishProvider
import kotlin.random.Random


class FishRepository {
    companion object {
        suspend fun loadFishAndInsertBuffer() {
            val service= RetrofitClient.getRetrofitFishService()
            try {
                val response=service.getAllFish()
                val listFish=response.body()
                if(listFish!=null)
                    FishProvider.listFish=listFish
            }catch (ex: Exception){
                Log.d("TAG", "Excepción en "+ex)
            }

        }


        fun getRandomFishFromBuffer(restricted:List<Fish?>): Fish?{
            var fish : Fish?=null
            if(!FishProvider.listFish.isEmpty()){
                val random = Random.nextInt(FishProvider.listFish.size)
                //Log.d("TAG3", "FishRepository dice: el random es: "+random.toString())
                fish= FishProvider.listFish[random]
                //Log.d("TAG3", "FishRepository dice: el random es: "+fish.toString())
                for(i in restricted){
                    if(fish.name.equals(i?.name)) {
                        getRandomFishFromBuffer(restricted)
                    }
                }
            }else{
                Log.d("TAG3", "la lista esta vacia")
            }
            return fish
        }

        fun getFishFromSpecieIdInBuffer(id: Int): Fish?{
            //Log.d("TAG", "FishRepository dice: pasa por getFishFromSpecieIdInBuffer con id: "+id)
            var fish: Fish?=null
            FishProvider.listFish.forEach{
                if(it.id!=null){
                    if(it.id==id) {
                        //Log.d("TAG", "FishRepository dice: encontrado el pez: "+id)
                        fish=it
                    }
                    //Log.d("TAG","FishRepository dice: Obtemido el pez: "+it.name)
                }
            }
            return fish
        }
    }//Final del cpompanion object
}