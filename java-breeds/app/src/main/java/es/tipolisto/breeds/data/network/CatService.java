package es.tipolisto.breeds.data.network;

import android.util.Log;

import java.util.List;

import es.tipolisto.breeds.data.buffer.ArrayDataSourceProvider;
import es.tipolisto.breeds.data.model.CatSimple;
import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.ImageCat;
import es.tipolisto.breeds.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatService {
    private RetrofitClient retrofitClient;
    public CatService (){
        retrofitClient=new RetrofitClient();
    }

    public void getAllCatsAndInsertOnBuffer(){
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();
        Call<List<Cat>> callCatListResponse=iCatsApiService.getAllCats();
        callCatListResponse.enqueue(new Callback<List<Cat>>() {
            @Override
            public void onResponse(Call<List<Cat>> call, Response<List<Cat>> response) {
                if(response.isSuccessful()){
                    List<Cat> listCats=response.body();
                    //Log.d("Mensaje", "recividos: "+listCats.size());
                    ArrayDataSourceProvider.listAllcats=response.body();
                }
            }
            @Override
            public void onFailure(Call<List<Cat>> call, Throwable t) {
                ArrayDataSourceProvider.listAllcats=null;
            }
        });

    }


    public CatSimple getCatByBreedInternet(String breedId){
        ICatsApi iCatsApiService= retrofitClient.getCatApiService();
        //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
        Call<List<CatSimple>> callCat=iCatsApiService.getSimpleCatById(breedId);
        callCat.enqueue(new Callback<List<CatSimple>>() {
            @Override
            public void onResponse(Call<List<CatSimple>> call, Response<List<CatSimple>> response) {
                if(response.isSuccessful()){
                    List<CatSimple>listCat=response.body();
                    if (listCat.size()>0)
                        ArrayDataSourceProvider.cat=listCat.get(0);
                    else
                        ArrayDataSourceProvider.cat=null;
                }
            }
            @Override
            public void onFailure(Call<List<CatSimple>> call, Throwable t) {
                ArrayDataSourceProvider.cat=null;
            }
        });
        return ArrayDataSourceProvider.cat;
    }

    public CatSimple getdataBreedSimpleCatFromInternet(String breedId){
        //Obtenemos la imagen
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();
        //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
        Call<List<CatSimple>> callCat=iCatsApiService.getSimpleCatById(breedId);
        callCat.enqueue(new Callback<List<CatSimple>>() {
            @Override
            public void onResponse(Call<List<CatSimple>> call, Response<List<CatSimple>> response) {
                if(response.isSuccessful()){
                    //La url en realidad devuelve un array con 1 solo objeto cat
                    List<CatSimple> listcats=response.body();
                    if(listcats.size()>0){
                        ArrayDataSourceProvider.cat=listcats.get(0);
                    }else{
                        ArrayDataSourceProvider.cat=null;
                    }

                }
            }
            @Override
            public void onFailure(Call<List<CatSimple>> call, Throwable t) {
                ArrayDataSourceProvider.cat=null;
            }
        });
        return  ArrayDataSourceProvider.cat;
    }
    /*public void setImageCat(final List<Cat> listCats){
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();
        for (Cat cat: listCats){
            //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
            Call<ImageCat> callImageCat=iCatsApiService.getImageCatById(cat.getReference_image_id());
            callImageCat.enqueue(new Callback<ImageCat>() {
                @Override
                public void onResponse(Call<ImageCat> call, Response<ImageCat> response) {
                    if(response.body()==null) Log.d(Constants.LOG, "En catService: response body es null");
                    else{
                        Log.d(Constants.LOG, "En CatService: Obtenidos datos");
                        ImageCat image =response.body();
                        Log.d(Constants.LOG, "En CatService: obtenedida la Image: "+ image.toString());
                        //ArrayDataSourceProvider.imageCat=image;
                        cat.setImage(image);
                    }
                }
                @Override
                public void onFailure(Call<ImageCat> call, Throwable t) {
                    Log.d(Constants.LOG, "En catService: No obtenida");
                }
            });
        }
    }*/

    //https://api.thecatapi.com/v1/images/0XYvRd7oD
    public void setImageCatFromInternetByreferenceImageId(){
        List<Cat> listCats=ArrayDataSourceProvider.listAllcats;
        //final Image[] image = new Image[1];
        ICatsApi iCatsApiService=retrofitClient.getCatApiService();


        for (int i=0;i<listCats.size();i++) {
            Cat cat=listCats.get(i);
            //Con el retorfit tan solo obtenemos el path de la imagen de internet y se la metemeos al imageView
            Call<ImageCat> callImageCat = iCatsApiService.getImageCatById(cat.getReference_image_id());
            int finalI = i;
            callImageCat.enqueue(new Callback<ImageCat>() {
                @Override
                public void onResponse(Call<ImageCat> call, Response<ImageCat> response) {
                    if (response.body() == null)
                        Log.d(Constants.LOG, "En catService: response body es null");
                    else {
                        Log.d(Constants.LOG, "En CatService: Obtenidos datos");
                        ImageCat image = response.body();
                        Log.d(Constants.LOG, "En CatService: obtenedida la Image: " + image.toString());
                        cat.setImage(image);
                        listCats.set(finalI,cat);
                        //ArrayDataSourceProvider.imageCat = image;
                        ArrayDataSourceProvider.listAllcats=listCats;
                    }
                }

                @Override
                public void onFailure(Call<ImageCat> call, Throwable t) {
                    Log.d(Constants.LOG, "En catService: No obtenida");
                }
            });
        }
        //cat.setImage(ArrayDataSourceProvider.imageCat);

        /*callCat.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                if(response.isSuccessful()){
                    //La url en realidad devuelve un array con 1 solo objeto cat
                    Image image=response.body();
                    Log.d(Constants.LOG, "En CatService: obtenedida la Image: "+image.toString());
                    cat.setImage(image);
                }
            }
            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                Log.d(Constants.LOG, "En CatService: no se pudo obetenr la imagen de "+cat.getReference_image_id());
            }
        });*/
    }

}
/*
    Obtener imagen por internet por id:
    {
        "id":"0XYvRd7oD",
        "width":1204,"height":1445,
        "url":"https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
        "breeds":[{
            "weight":{"imperial":"7  -  10","metric":"3 - 5"},
            "id":"abys","name":"Abyssinian",
            "temperament":"Active, Energetic, Independent, Intelligent, Gentle",
            "origin":"Egypt",
            "country_codes":"EG",
            "country_code":"EG",
            "life_span":"14 - 15",
            "wikipedia_url":"https://en.wikipedia.org/wiki/Abyssinian_(cat)"
        }]
     }

 */

/*  Pinta del objeto Cat (breed_id)
    Devuelve una lista con un objeto cat a partir del id que le mandamos por la url (el id lo yenemos ya predefinido en un hasmap)
    https://api.thecatapi.com/v1/images/search?breed_ids=3
    @GET("images/search")
    Call<List<Cat>> getcatById(@Query("breed_ids") String breed_id);

     te devuelve una array que contiene un solo Cat:
     [
        {
            breads [weighr, is, name, temperament,etc],
            id,
            url,
            width,
            height,
        }
     ]


     ----------------------------

     Pinta del objeto CatList
        private Image image;
        private String name;

[
      {
        "weight": {
            "imperial": "7  -  10",
            "metric": "3 - 5"
         },
        "id": "abys",
        "name": "Abyssinian",
        "cfa_url": "http://cfa.org/Breeds/BreedsAB/Abyssinian.aspx",
        "vetstreet_url": "http://www.vetstreet.com/cats/abyssinian",
        "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/abyssinian",
        "temperament": "Active, Energetic, Independent, Intelligent, Gentle",
        "origin": "Egypt",
        "country_codes": "EG",
        "country_code": "EG",
        "description": "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        "life_span": "14 - 15",
        "indoor": 0,
        "lap": 1,
        "alt_names": "",
        "adaptability": 5,
        "affection_level": 5,
        "child_friendly": 3,
        "dog_friendly": 4,
        "energy_level": 5,
        "grooming": 1,
        "health_issues": 2,
        "intelligence": 5,
        "shedding_level": 2,
        "social_needs": 5,
        "stranger_friendly": 5,
        "vocalisation": 1,
        "experimental": 0,
        "hairless": 0,
        "natural": 1,
        "rare": 0,
        "rex": 0,
        "suppressed_tail": 0,
        "short_legs": 0,
        "wikipedia_url": "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
        "hypoallergenic": 0,
        "reference_image_id": "0XYvRd7oD",
        "image": {
            "id": "0XYvRd7oD",
            "width": 1204,
            "height": 1445,
            "url": "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
        }
     },
     {},
     {},
     {},
     {},
     {},
     {}
]


 */