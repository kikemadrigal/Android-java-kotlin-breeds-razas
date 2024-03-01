package es.tipolisto.breeds.data.models.fish

import com.google.gson.annotations.SerializedName

data class Img_src_set(
    @SerializedName("1.5x") var image1Coma5x: String?=null,
    @SerializedName("2x") var image2x: String?=null
)