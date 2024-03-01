package es.tipolisto.breeds.data.network

import es.tipolisto.breeds.data.models.cat.Cat
import es.tipolisto.breeds.data.models.cat.ImageCat

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ICatApiService {
    /*
    Hay que tener bien claro la respuesta que te dan las url, en el caso de
    Web: https://docs.thecatapi.com/
    Url base:https://api.thecatapi.com/v1/

    Haz click en el encale para ver el json que te devuelve, instálate la extensión JSON Formatter en el navegador para verlo bien
    El siguiente enlace te devuelve una lista de objetos "raza" ordenados alfabéticamente, en nuestro caso a este objeto lo llamaremos Cat
    https://api.thecatapi.com/v1/breeds
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
        "reference_image_id": "0XYvRd7oD"
        },
        {
        "weight": {
        "imperial": "7 - 10",
        "metric": "3 - 5"
        },
        "id": "aege",
        "name": "Aegean",
        "vetstreet_url": "http://www.vetstreet.com/cats/aegean-cat",
        "temperament": "Affectionate, Social, Intelligent, Playful, Active",
        "origin": "Greece",
        "country_codes": "GR",
        "country_code": "GR",
        "description": "Native
     */
    @GET("breeds")
    suspend fun getAllBreeds(): Response<List<Cat>>

    /*Devuelve una Image de un cat dado un reference_image_id que es una propiedad de un objeto Cat:
    https://api.thecatapi.com/v1/images/4lXnnfxac
    {
    "id": "4lXnnfxac",
    "url": "https://cdn2.thecatapi.com/images/4lXnnfxac.jpg",
    "breeds": [
        {
            "weight": {
            "imperial": "6 - 12",
            "metric": "3 - 5"
            },
            "id": "bure",
            "name": "Burmese",
            "cfa_url": "http://cfa.org/Breeds/BreedsAB/Burmese.aspx",
            "vetstreet_url": "http://www.vetstreet.com/cats/burmese",
            "vcahospitals_url": "https://vcahospitals.com/know-your-pet/cat-breeds/burmese",
            "temperament": "Curious, Intelligent, Gentle, Social, Interactive, Playful, Lively",
            "origin": "Burma",
            "country_codes": "MM",
            "country_code": "MM",
            "description": "Burmese love being with people, playing with them, and keeping them entertained. They crave close physical contact and abhor an empty lap. They will follow their humans from room to room, and sleep in bed with them, preferably under the covers, cuddled as close as possible. At play, they will turn around to see if their human is watching and being entertained by their crazy antics.",
            "life_span": "15 - 16",
            "indoor": 0,
            "lap": 1,
            "alt_names": "",
            "adaptability": 5,
            "affection_level": 5,
            "child_friendly": 4,
            "dog_friendly": 5,
            "energy_level": 4,
            "grooming": 1,
            "health_issues": 3,
            "intelligence": 5,
            "shedding_level": 3,
            "social_needs": 5,
            "stranger_friendly": 5,
            "vocalisation": 5,
            "experimental": 0,
            "hairless": 0,
            "natural": 0,
            "rare": 0,
            "rex": 0,
            "suppressed_tail": 0,
            "short_legs": 0,
            "wikipedia_url": "https://en.wikipedia.org/wiki/Burmese_(cat)",
            "hypoallergenic": 1,
            "reference_image_id": "4lXnnfxac"
        }
    ],
    "width": 1250,
    "height": 650
    }
    */
    @GET("images/{reference_image_id}")
    suspend fun getImageCat(@Path ("reference_image_id") reference_image_id:String): Response<ImageCat>
}