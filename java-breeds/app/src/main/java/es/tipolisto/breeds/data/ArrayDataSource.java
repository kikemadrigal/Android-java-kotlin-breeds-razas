package es.tipolisto.breeds.data;

import java.util.HashMap;
import java.util.Map;

public class ArrayDataSource {
    public static String[] catBreeds={
            "Abyssinian",
            "Aegean",
            "American Bobtail",
            "American Curl",
            "American Shorthair",
            "American Wirehair",
            "Arabian Mau",
            "Australiam Mist",
            "Balinese",
            "Bambino",
            "Bengal",
            "Birman",
            "Bombay",
            "British Longhair",
            "British Shorthair",
            "Burmese",
            "Burmilla",
            "California Spangled",
            "Chantilly-Tiffany",
            "Chartreux",
            "Chausie",
            "Cheetoh",
            "Colorpoint Shorthair",
            "Cornish Rex",
            "Cymric",
            "Cyprus",
            "Devon Rex",
            "Donskoy",
            "Dragon Li",
            "Egyptian Mau",
            "European Burmese",
            "Exotic Shorthair",
            "Havana Brown",
            "Himalayan",
            "Japanese Bobtail",
            "Javanese",
            "Khao Manee",
            "Korat",
            "Kurilian",
            "LaPerm",
            "Maine Coon",
            "Malayan",
            "Manx",
            "Munchkin",
            "Nebelung",
            "Norwegian Forest Cat",
            "Ocicat",
            "Oriental",
            "Persian",
            "Pixie-bob",
            "Ragamuffin",
            "Ragdoll",
            "Russian Blue",
            "Savannah",
            "Scottish Fold",
            "Selkirk Rex",
            "Siamese",
            "Siberian",
            "Singapura",
            "Snowshoe",
            "Somali",
            "Sphynx",
            "Tonkinese",
            "Toyger",
            "Turkish Angora",
            "Turkish Van",
            "York Chocolate"
    };

    public static String[] catIds={
            "abys",
         "aege",
         "abob",
         "acur",
         "asho",
         "awir",
         "amau",
         "amis",
         "bali",
         "bamb",
         "beng",
         "birm",
         "bomb",
         "bslo",
         "bsho",
         "bure",
         "buri",
         "cspa",
         "ctif",
         "char",
         "chau",
         "chee",
         "csho",
         "crex",
         "cymr",
         "cypr",
         "drex",
         "dons",
         "lihu",
         "emau",
         "ebur",
         "esho",
         "hbro",
         "hima",
         "jbob",
         "java",
         "khao",
         "kora",
         "kuri",
         "lape",
         "mcoo",
         "mala",
         "manx",
         "munc",
         "nebe",
         "norw",
         "ocic",
         "orie",
         "pers",
         "pixi",
         "raga",
         "ragd",
         "rblu",
         "sava",
         "sfol",
         "srex",
         "siam",
         "sibe",
         "sing",
         "snow",
         "soma",
         "sphy",
         "tonk",
         "toyg",
         "tang",
         "tvan",
         "ycho",
    };


    private static HashMap<String,String> mapCatIdNames=new HashMap<String,String>();

    public static HashMap<String, String> getMapCatIdNames() {
        mapCatIdNames.clear();
        mapCatIdNames.put( "abys","Abyssinian");
        mapCatIdNames.put( "aege","Aegean");
        mapCatIdNames.put( "abob","American Bobtail");
        mapCatIdNames.put( "acur","American Curl");
        mapCatIdNames.put( "asho","American Shorthair");
        mapCatIdNames.put( "awir","American Wirehair");
        mapCatIdNames.put( "amau","Arabian Mau");
        mapCatIdNames.put( "amis","Australiam Mist");
        mapCatIdNames.put( "bali","Balinese");
        mapCatIdNames.put( "bamb","Bambino");
        mapCatIdNames.put( "beng","Bengal");
        mapCatIdNames.put( "birm","Birman");
        mapCatIdNames.put( "bomb","Bombay");
        mapCatIdNames.put( "bslo","British Longhair");
        mapCatIdNames.put( "bsho","British Shorthair");
        mapCatIdNames.put( "bure","Burmese");
        mapCatIdNames.put( "buri","Burmilla");
        mapCatIdNames.put( "cspa","California Spangled");
        mapCatIdNames.put( "ctif","Chantilly-Tiffany");
        mapCatIdNames.put( "char","Chartreux");
        mapCatIdNames.put( "chau","Chausie");
        mapCatIdNames.put( "chee","Cheetoh");
        mapCatIdNames.put( "csho","Colorpoint Shorthair");
        mapCatIdNames.put( "crex","Cornish Rex");
        mapCatIdNames.put( "cymr","Cymric");
        mapCatIdNames.put( "cypr","Cyprus");
        mapCatIdNames.put( "drex","Devon Rex");
        mapCatIdNames.put( "dons","Donskoy");
        mapCatIdNames.put( "lihu","Dragon Li");
        mapCatIdNames.put( "emau","Egyptian Mau");
        mapCatIdNames.put( "ebur","European Burmese");
        mapCatIdNames.put( "esho","Exotic Shorthair");
        mapCatIdNames.put( "hbro","Havana Brown");
        mapCatIdNames.put( "hima","Himalayan");
        mapCatIdNames.put( "jbob","Japanese Bobtail");
        mapCatIdNames.put( "java","Javanese");
        mapCatIdNames.put( "khao","Khao Manee");
        mapCatIdNames.put( "kora","Korat");
        mapCatIdNames.put( "kuri","Kurilian");
        mapCatIdNames.put( "lape","LaPerm");
        mapCatIdNames.put( "mcoo","Maine Coon");
        mapCatIdNames.put( "mala","Malayan");
        mapCatIdNames.put( "manx","Manx");
        mapCatIdNames.put( "munc","Munchkin");
        mapCatIdNames.put( "nebe","Nebelung");
        mapCatIdNames.put( "norw","Norwegian Forest Cat");
        mapCatIdNames.put( "ocic","Ocicat");
        mapCatIdNames.put( "orie","Oriental");
        mapCatIdNames.put( "pers","Persian");
        mapCatIdNames.put( "pixi","Pixie-bob");
        mapCatIdNames.put( "raga","Ragamuffin");
        mapCatIdNames.put( "ragd","Ragdoll");
        mapCatIdNames.put( "rblu","Russian Blue");
        mapCatIdNames.put( "sava","Savannah");
        mapCatIdNames.put( "sfol","Scottish Fold");
        mapCatIdNames.put( "srex","Selkirk Rex");
        mapCatIdNames.put( "siam","Siamese");
        mapCatIdNames.put( "sibe","Siberian");
        mapCatIdNames.put( "sing","Singapura");
        mapCatIdNames.put( "snow","Snowshoe");
        mapCatIdNames.put( "soma","Somali");
        mapCatIdNames.put( "sphy","Sphynx");
        mapCatIdNames.put( "tonk","Tonkinese");
        mapCatIdNames.put( "toyg","Toyger");
        mapCatIdNames.put( "tang","Turkish Angora");
        mapCatIdNames.put( "tvan","Turkish Van");
        mapCatIdNames.put( "ycho","York Chocolate");

        return mapCatIdNames;
    }

    public static String[] dogsBreeds={
            "Affenpinscher",
            "African",
            "Airedale",
            "Akita",
            "Appenzeller",
            "Shepherd Australian",
            "Bassenji",
            "Beagle",
            "Bluetick",
            "Borzoi",
            "Bouvier",
            "Boxer",
            "Brabancon",
            "Briard",
            "Norwegian Buhund",
            "Boston BUlldog",
            "English Bulldog",
            "French Bulldog",
            "Staffordshire Bullterrier",
            "Australian Cattledog",
            "Chihuahua",
            "Chow",
            "Clumber",
            "Cockapoo",
            "Border Collie",
            "Coonhound",
            "Cardigan Corgi",
            "Cotondetulear",
            "Dachshund",
            "Dalmatian",
            "Great Dane",
            "Scottish Deerhound",
            "Dhole",
            "Dingo",
            "Doberman"
    };

    private static HashMap<Integer,String> hasMapRecordList=new HashMap<Integer,String>();
    public static HashMap<Integer, String> getMaprecordList() {
        hasMapRecordList.clear();
        hasMapRecordList.put( 1,"Neo");
        hasMapRecordList.put( 2,"Tinity");
        hasMapRecordList.put( 3,"Morfeo");
        hasMapRecordList.put( 4,"Cifra");
        hasMapRecordList.put( 5,"Smith agent");
        hasMapRecordList.put( 6,"Oraculo");
        hasMapRecordList.put( 7,"MEROVINGIO");
        hasMapRecordList.put( 8,"PERSEPHONE");
        hasMapRecordList.put( 9,"Arquitect");

        return hasMapRecordList;
    };


}
