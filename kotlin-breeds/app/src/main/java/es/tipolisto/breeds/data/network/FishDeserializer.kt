package es.tipolisto.breeds.data.network

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import es.tipolisto.breeds.data.models.fish.Fish
import es.tipolisto.breeds.data.models.fish.Img_src_set
import es.tipolisto.breeds.data.models.fish.Meta
import java.lang.reflect.Type

class FishDeserializer() :JsonDeserializer<Fish> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ):Fish {
        var id=0
        var name=""
        var url=""
        //var img_src_set: Img_src_set?=null
        var img_src_set: String?=null
        var meta:String?=null
        if (json == null || json.isJsonNull || json !is JsonObject) {
            null
        } else {
             val fish = json.asJsonObject
             id = fish.get("id").asInt
             name = fish.get("name").asString
             url = fish.get("url").asString
             if(fish.get("img_src_set").isJsonObject){
                 img_src_set = fish.get("img_src_set").toString()
                 img_src_set=getURL(img_src_set)
                 //img_src_set= fish.get("img_src_set") as Img_src_set?
             }
             else
                 img_src_set = "Not image"
            if(fish.get("img_src_set").isJsonObject) {
                meta = fish.get("meta").toString()
            }
            else
                meta="Not data"

        }
        return Fish(id, name, url, img_src_set, meta)
    }


    private fun getURL(img_src_set:String):String{
        var url:String=""
        val split=img_src_set.split("\"")
        split.forEach(){
            if(it.contains("https")){
                url=it
            }
        }
        return url
    }
}