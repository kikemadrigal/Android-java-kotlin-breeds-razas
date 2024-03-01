package es.tipolisto.breeds.data.models.cat

data class ImageCat(
    val id: String,
    val url: String,
    val breeds: List<BreedCat>,
    val width: Int,
    val height: Int
)
