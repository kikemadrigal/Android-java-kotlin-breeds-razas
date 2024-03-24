package es.tipolisto.breeds.data.providers

class ArrayDataSourceProvider {
    companion object{
        private var hasMapRecordList = LinkedHashMap<Int, String>()
        fun getMapRecordList(): LinkedHashMap<Int, String> {
            ArrayDataSourceProvider.hasMapRecordList.clear()
            ArrayDataSourceProvider.hasMapRecordList.put(45000, "Neo")
            ArrayDataSourceProvider.hasMapRecordList.put(41400, "Tinity")
            ArrayDataSourceProvider.hasMapRecordList.put(38553, "Morfeo")
            ArrayDataSourceProvider.hasMapRecordList.put(35551, "Cifra")
            ArrayDataSourceProvider.hasMapRecordList.put(32504, "Smith agent")
            ArrayDataSourceProvider.hasMapRecordList.put(1422, "Oraculo")
            ArrayDataSourceProvider.hasMapRecordList.put(50, "Merovingio")
            ArrayDataSourceProvider.hasMapRecordList.put(40, "Persephone")
            ArrayDataSourceProvider.hasMapRecordList.put(30, "Arquitect")
            ArrayDataSourceProvider.hasMapRecordList.put(20, "Anakin Skywalker")
            ArrayDataSourceProvider.hasMapRecordList.put(23, "Luke Skywalker")
            ArrayDataSourceProvider.hasMapRecordList.put(20, "Han solo")
            ArrayDataSourceProvider.hasMapRecordList.put(18, "Leia Organa")
            ArrayDataSourceProvider.hasMapRecordList.put(4, "Yoda")
            ArrayDataSourceProvider.hasMapRecordList.put(3, "Chewbacca")
            return ArrayDataSourceProvider.hasMapRecordList
        }
    }
}