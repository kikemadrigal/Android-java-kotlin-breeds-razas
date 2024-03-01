package es.tipolisto.breeds.data.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import es.tipolisto.breeds.data.models.dog.Dog
import java.lang.reflect.Type

class DogDeserializer() :JsonDeserializer<Dog> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ):Dog? {
        var weight="-"
        var height="-"
        var id=0
        var name="-"
        var bred_for="-"
        var breed_group="-"
        var life_span="-"
        var temperament="-"
        var origin="-"
        var reference_image_id="-"


        if (json == null || json.isJsonNull || json !is JsonObject) {
            null
        } else {
             val dog = json.asJsonObject

             id=dog.get("id").asInt
             name = dog.get("name").asString
             if(dog.get("bred_for")!=null)bred_for=dog.get("bred_for").asString
             if(dog.get("breed_group")!=null)breed_group=dog.get("breed_group").asString
             if(dog.get("life_span")!=null)life_span=dog.get("life_span").asString
             if(dog.get("temperament")!=null)temperament=dog.get("temperament").asString
             if(dog.get("origin")!=null)origin= dog.get("origin").asString
             if(dog.get("reference_image_id")!=null)reference_image_id = dog.get("reference_image_id").asString


             if(dog.get("weight").isJsonObject){
                 weight = dog.get("weight").toString()
             }
             else
                 weight = "Not weight"
            if(dog.get("height").isJsonObject) {
                height = dog.get("height").toString()
            }
            else
                height="Not height"
        }
        return Dog(
            weight,
            height,
            id,
            name,
            bred_for,
            breed_group,
            life_span,
            temperament,
            origin,
            reference_image_id,
            null
        )
    }



}