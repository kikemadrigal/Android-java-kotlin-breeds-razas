package es.tipolisto.breeds.data.models.dog

import com.google.gson.annotations.SerializedName

data class Dog(
    //val weight: WeightDog,
    //val height: HeightDog,
    @SerializedName("weight") var weight: String?=null,
    @SerializedName("height") var height: String?=null,
    var id:Int,
    var name:String,
    val bred_for:String,
    val breed_group:String,
    val life_span:String,
    val temperament:String,
    val origin:String,
    val reference_image_id:String,

    var imageDog:ImageDog?
)
