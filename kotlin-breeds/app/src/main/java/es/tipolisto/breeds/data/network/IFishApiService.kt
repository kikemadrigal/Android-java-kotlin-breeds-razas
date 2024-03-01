package es.tipolisto.breeds.data.network

import es.tipolisto.breeds.data.models.fish.Fish
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface IFishApiService {
    /*
    1- obtenemos una lista de objetos Fish: https://rapidapi.com/myapos--FqlEzvrlv/api/fish-species/
    [
        0:{
            id:1
            name:"Airbreathing catfish"
            url:"https://en.wikipedia.org/wiki/Airbreathing_catfish"
            img_src_set:{
                    1.5x:"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Clarias_batrachus-ZOO.Brno.jpg/330px-Clarias_batrachus-ZOO.Brno.jpg"
                    2x:"https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Clarias_batrachus-ZOO.Brno.jpg/440px-Clarias_batrachus-ZOO.Brno.jpg"
            }
            meta:{
                genera:"Bathyclarias, Channallabes, Clariallabes, Clarias, Dinotopterus, Dolichallabes, Encheloclarias, Gymnallabes, Heterobranchus, Horaglanis, Platyallabes, Platyclarias, Pseudotanganikallabes, Tanganikallabes, Uegitglanis, Xenoclarias",
                scientific_classification:{
                    domain:"eukaryota"
                    kingdom:"animalia"
                    phylum:"chordata"
                    class:"actinopterygii"
                    order:"siluriformes"
                    superfamily:"siluroidea"
                    family:"clariidaebonaparte,_1846"
                }
            }

         },
         1:{
            ....
         },
         ....
    ]
     */
    @GET("fishes")
    //@GET("fish/Tarpon")
    @Headers("X-RapidAPI-Key: 3a4e508e58mshc8f669e5eae0e18p1b6153jsn2bf8cb0f5d54", "X-RapidAPI-Host: fish-species.p.rapidapi.com")
    suspend fun getAllFish(): Response<List<Fish>>

    @GET("fish/{name}")
    suspend fun getImageFish(@Path("name") name:String): Response<Fish>
}