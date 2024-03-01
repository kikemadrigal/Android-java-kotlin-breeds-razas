package es.tipolisto.breeds.data.models.fish

import com.google.gson.annotations.SerializedName

data class Fish(
    var id: Int,
    var name: String,
    @SerializedName("url")var urlWiki: String,
    //@SerializedName("img_src_set") var img_src_set: Img_src_set?=null,
    @SerializedName("img_src_set") var img_src_set: String?=null,
    var meta: String?=null,
)