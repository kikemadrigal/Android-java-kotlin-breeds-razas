package es.tipolisto.breeds.data.models

data class BreedsDog (val weight: Weight? = null,
                      val height: Height? = null,
                      val id:Int = 0,
                      val name: String? = null,
                      val country_code: String? = null,
                      val bred_for: String? = null,
                      val breed_group: String? = null,
                      val life_span: String? = null,
                      val temperament: String? = null,
                      val reference_image_id: String? = null)