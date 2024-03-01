package es.tipolisto.breeds.data.providers

class ArrayDataSourceProvider {
    companion object{
        private var hasMapRecordList = HashMap<Int, String>()
        fun getMapRecordList(): HashMap<Int, String> {
            ArrayDataSourceProvider.hasMapRecordList.clear()
            ArrayDataSourceProvider.hasMapRecordList.put(45000, "Neo")
            ArrayDataSourceProvider.hasMapRecordList.put(21400, "Tinity")
            ArrayDataSourceProvider.hasMapRecordList.put(10553, "Morfeo")
            ArrayDataSourceProvider.hasMapRecordList.put(9551, "Cifra")
            ArrayDataSourceProvider.hasMapRecordList.put(5004, "Smith agent")
            ArrayDataSourceProvider.hasMapRecordList.put(1422, "Oraculo")
            ArrayDataSourceProvider.hasMapRecordList.put(64, "Merovingio")
            ArrayDataSourceProvider.hasMapRecordList.put(23, "Persephone")
            ArrayDataSourceProvider.hasMapRecordList.put(13, "Arquitect")
            ArrayDataSourceProvider.hasMapRecordList.put(4, "Anakin Skywalker")
            ArrayDataSourceProvider.hasMapRecordList.put(51, "Luke Skywalker")
            ArrayDataSourceProvider.hasMapRecordList.put(6, "Han solo")
            ArrayDataSourceProvider.hasMapRecordList.put(8, "Leia Organa")
            ArrayDataSourceProvider.hasMapRecordList.put(11, "Yoda")
            ArrayDataSourceProvider.hasMapRecordList.put(24, "Chewbacca")
            return ArrayDataSourceProvider.hasMapRecordList
        }
    }
}