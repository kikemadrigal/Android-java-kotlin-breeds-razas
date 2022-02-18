package es.tipolisto.breeds.data.buffer;

import java.util.HashMap;
import java.util.List;

import es.tipolisto.breeds.data.model.Cat;
import es.tipolisto.breeds.data.model.CatListResponse;
import es.tipolisto.breeds.data.model.DogResponse;

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
    private static HashMap<String,String> mapDogIdNames=new HashMap<String,String>();

    public static HashMap<String, String> getMapDogIdNames() {
        mapDogIdNames.put("1","Affenpinscher");
        mapDogIdNames.put("2", "Afghan Hound");
        mapDogIdNames.put("3","African Hunting Dog");
        mapDogIdNames.put("4","Airedale Terrier");
        mapDogIdNames.put("5","Akbash Dog");
        mapDogIdNames.put("6","Akita");
        mapDogIdNames.put("7","Alapaha Blue Blood Bulldog");
        mapDogIdNames.put("8","Alaskan Husky");
        mapDogIdNames.put("9","Alaskan Malamute");
        mapDogIdNames.put("10","American Bulldog");
        mapDogIdNames.put("11","American Bully");
        mapDogIdNames.put("12","American Eskimo Dog");
        mapDogIdNames.put("13","American Eskimo Dog (Miniature)");
        mapDogIdNames.put("14","American Foxhound");
        mapDogIdNames.put("15","American Pit Bull Terrier");
        mapDogIdNames.put("16","American Staffordshire Terrier");
        mapDogIdNames.put("17","American Water Spaniel");
        mapDogIdNames.put("18","Anatolian Shepherd Dog");
        mapDogIdNames.put("19","Appenzeller Sennenhund");
        mapDogIdNames.put("21","Australian Cattle Dog");
        mapDogIdNames.put("22","Australian Kelpie");
        mapDogIdNames.put("23","Australian Shepherd");
        mapDogIdNames.put("24","Australian Terrier");
        mapDogIdNames.put("25","Azawakh");
        mapDogIdNames.put("26","Barbet");
        mapDogIdNames.put("28","Basenji");
        mapDogIdNames.put("29","Basset Bleu de Gascogne");
        mapDogIdNames.put("30","Basset Hound");
        mapDogIdNames.put("31","Beagle");
        mapDogIdNames.put("32","Bearded Collie");
        mapDogIdNames.put("33","Beauceron");
        mapDogIdNames.put("34","Bedlington Terrier");
        mapDogIdNames.put("36","Belgian Malinois");
        mapDogIdNames.put("38","Belgian Tervuren");
        mapDogIdNames.put("41","Bernese Mountain Dog");
        mapDogIdNames.put("42","Bichon Frise");
        mapDogIdNames.put("43","Black and Tan Coonhound");
        mapDogIdNames.put("45","Bloodhound");
        mapDogIdNames.put("47","Bluetick Coonhound");
        mapDogIdNames.put("48","Boerboel");
        mapDogIdNames.put("50","Border Collie");
        mapDogIdNames.put("51","Border Terrier");
        mapDogIdNames.put("53","Boston Terrier");
        mapDogIdNames.put("54","Bouvier des Flandres");
        mapDogIdNames.put("55","Boxer");
        mapDogIdNames.put("56","Boykin Spaniel");
        mapDogIdNames.put("57","Bracco Italiano");
        mapDogIdNames.put("58","Briard");
        mapDogIdNames.put("59","Brittany");
        mapDogIdNames.put("61","Bull Terrier");
        mapDogIdNames.put("62","Bull Terrier (Miniature)");
        mapDogIdNames.put("64","Bullmastiff");
        mapDogIdNames.put("65","Cairn Terrier");
        mapDogIdNames.put("67","Cane Corso");
        mapDogIdNames.put("68","Cardigan Welsh Corgi");
        mapDogIdNames.put("69","Catahoula Leopard Dog");
        mapDogIdNames.put("70","Caucasian Shepherd (Ovcharka)");
        mapDogIdNames.put("71","Cavalier King Charles Spaniel");
        mapDogIdNames.put("76","Chesapeake Bay Retriever");
        mapDogIdNames.put("78","Chinese Crested");
        mapDogIdNames.put("79","Chinese Shar-Pei");
        mapDogIdNames.put("80","Chinook");
        mapDogIdNames.put("81","Chow Chow");
        mapDogIdNames.put("84","Clumber Spaniel");
        mapDogIdNames.put("86","Cocker Spaniel");
        mapDogIdNames.put("87","Cocker Spaniel (American)");
        mapDogIdNames.put("89","Coton de Tulear");
        mapDogIdNames.put("92","Dalmatian");
        mapDogIdNames.put("94","Doberman Pinscher");
        mapDogIdNames.put("95","Dogo Argentino");
        mapDogIdNames.put("98","Dutch Shepherd");
        mapDogIdNames.put("101","English Setter");
        mapDogIdNames.put("102","English Shepherd");
        mapDogIdNames.put("103","English Springer Spaniel");
        mapDogIdNames.put("104","English Toy Spaniel");
        mapDogIdNames.put("105","English Toy Terrier");
        mapDogIdNames.put("107","Eurasier");
        mapDogIdNames.put("108","Field Spaniel");
        mapDogIdNames.put("110","Finnish Lapphund");
        mapDogIdNames.put("111","Finnish Spitz");
        mapDogIdNames.put("113","French Bulldog");
        mapDogIdNames.put("114","German Pinscher");
        mapDogIdNames.put("115","German Shepherd Dog");
        mapDogIdNames.put("116","German Shorthaired Pointer");
        mapDogIdNames.put("119","Giant Schnauzer");
        mapDogIdNames.put("120","Glen of Imaal Terrier");
        mapDogIdNames.put("121","Golden Retriever");
        mapDogIdNames.put("123","Gordon Setter");
        mapDogIdNames.put("124","Great Dane");
        mapDogIdNames.put("125","Great Pyrenees");
        mapDogIdNames.put("127","Greyhound");
        mapDogIdNames.put("128","Griffon Bruxellois");
        mapDogIdNames.put("129","Harrier");
        mapDogIdNames.put("130","Havanese");
        mapDogIdNames.put("134","Irish Setter");
        mapDogIdNames.put("135","Irish Terrier");
        mapDogIdNames.put("137","Irish Wolfhound");
        mapDogIdNames.put("138","Italian Greyhound");
        mapDogIdNames.put("140","Japanese Chin");
        mapDogIdNames.put("141","Japanese Spitz");
        mapDogIdNames.put("142","Keeshond");
        mapDogIdNames.put("144","Komondor");
        mapDogIdNames.put("145","Kooikerhondje");
        mapDogIdNames.put("147","Kuvasz");
        mapDogIdNames.put("149","Labrador Retriever");
        mapDogIdNames.put("151","Lagotto Romagnolo");
        mapDogIdNames.put("153","Lancashire Heeler");
        mapDogIdNames.put("155","Leonberger");
        mapDogIdNames.put("156","Lhasa Apso");
        mapDogIdNames.put("161","Maltese");
        mapDogIdNames.put("165","Miniature American Shepherd");
        mapDogIdNames.put("167","Miniature Pinscher");
        mapDogIdNames.put("168","Miniature Schnauzer");
        mapDogIdNames.put("171","Newfoundland");
        mapDogIdNames.put("172","Norfolk Terrier");
        mapDogIdNames.put("176","Norwich Terrier");
        mapDogIdNames.put("177","Nova Scotia Duck Tolling Retriever");
        mapDogIdNames.put("178","Old English Sheepdog");
        mapDogIdNames.put("179","Olde English Bulldogge");
        mapDogIdNames.put("181","Papillon");
        mapDogIdNames.put("183","Pekingese");
        mapDogIdNames.put("184","Pembroke Welsh Corgi");
        mapDogIdNames.put("185","Perro de Presa Canario");
        mapDogIdNames.put("188","Pharaoh Hound");
        mapDogIdNames.put("189","Plott");
        mapDogIdNames.put("193","Pomeranian");
        mapDogIdNames.put("196","Poodle (Miniature)");
        mapDogIdNames.put("197","Poodle (Toy)");
        mapDogIdNames.put("201","Pug");
        mapDogIdNames.put("204","Puli");
        mapDogIdNames.put("205","Pumi");
        mapDogIdNames.put("207","Rat Terrier");
        mapDogIdNames.put("208","Redbone Coonhound");
        mapDogIdNames.put("209","Rhodesian Ridgeback");
        mapDogIdNames.put("210","Rottweiler");
        mapDogIdNames.put("211","Russian Toy");
        mapDogIdNames.put("212","Saint Bernard");
        mapDogIdNames.put("213","Saluki");
        mapDogIdNames.put("214","Samoyed");
        mapDogIdNames.put("216","Schipperke");
        mapDogIdNames.put("218","Scottish Deerhound");
        mapDogIdNames.put("219","Scottish Terrier");
        mapDogIdNames.put("221","Shetland Sheepdog");
        mapDogIdNames.put("222","Shiba Inu");
        mapDogIdNames.put("223","Shih Tzu");
        mapDogIdNames.put("225","Shiloh Shepherd");
        mapDogIdNames.put("226","Siberian Husky");
        mapDogIdNames.put("228","Silky Terrier");
        mapDogIdNames.put("232","Smooth Fox Terrier");
        mapDogIdNames.put("233","Soft Coated Wheaten Terrier");
        mapDogIdNames.put("235","Spanish Water Dog");
        mapDogIdNames.put("236","Spinone Italiano");
        mapDogIdNames.put("238","Staffordshire Bull Terrier");
        mapDogIdNames.put("239","Standard Schnauzer");
        mapDogIdNames.put("242","Swedish Vallhund");
        mapDogIdNames.put("243","Thai Ridgeback");
        mapDogIdNames.put("244","Tibetan Mastiff");
        mapDogIdNames.put("245","Tibetan Spaniel");
        mapDogIdNames.put("246","Tibetan Terrier");
        mapDogIdNames.put("248","Toy Fox Terrier");
        mapDogIdNames.put("250","Treeing Walker Coonhound");
        mapDogIdNames.put("251","Vizsla");
        mapDogIdNames.put("253","Weimaraner");
        mapDogIdNames.put("254","Welsh Springer Spaniel");
        mapDogIdNames.put("256","West Highland White Terrier");
        mapDogIdNames.put("257","Whippet");
        mapDogIdNames.put("258","White Shepherd");
        mapDogIdNames.put("259","Wire Fox Terrier");
        mapDogIdNames.put("260","Wirehaired Pointing Griffon");
        mapDogIdNames.put("261","Wirehaired Vizsla");
        mapDogIdNames.put("262","Xoloitzcuintli");
        mapDogIdNames.put("264","Yorkshire Terrier");

        return mapDogIdNames;
    }

    public static String[] dogsBreeds={
            "Affenpinscher",
            "Afghan Hound",
            "African Hunting Dog",
            "Airedale Terrier",
            "Akbash Dog",
            "Akita",
            "Alapaha Blue Blood Bulldog",
            "Alaskan Husky",
            "Alaskan Malamute",
            "American Bulldog",
            "American Bully",
            "American Eskimo Dog",
            "American Eskimo Dog (Miniature)",
            "American Foxhound",
            "American Pit Bull Terrier",
            "American Staffordshire Terrier",
            "American Water Spaniel",
            "Anatolian Shepherd Dog",
            "Appenzeller Sennenhund",
            "Australian Cattle Dog",
            "Australian Kelpie",
            "Australian Shepherd",
            "Australian Terrier",
            "Azawakh",
            "Barbet",
            "Basenji",
            "Basset Bleu de Gascogne",
            "Basset Hound",
            "Beagle",
            "Bearded Collie",
            "Beauceron",
            "Bedlington Terrier",
            "Belgian Malinois",
            "Belgian Tervuren",
            "Bernese Mountain Dog",
            "Bichon Frise",
            "Black and Tan Coonhound",
            "Bloodhound",
            "Bluetick Coonhound",
            "Boerboel",
            "Border Collie",
            "Border Terrier",
            "Boston Terrier",
            "Bouvier des Flandres",
            "Boxer",
            "Boykin Spaniel",
            "Bracco Italiano",
            "Briard",
            "Brittany",
            "Bull Terrier",
            "Bull Terrier (Miniature)",
            "Bullmastiff",
            "Cairn Terrier",
            "Cane Corso",
            "Cardigan Welsh Corgi",
            "Catahoula Leopard Dog",
            "Caucasian Shepherd (Ovcharka)",
            "Cavalier King Charles Spaniel",
            "Chesapeake Bay Retriever",
            "Chinese Crested",
            "Chinese Shar-Pei",
            "Chinook",
            "Chow Chow",
            "Clumber Spaniel",
            "Cocker Spaniel",
            "Cocker Spaniel (American)",
            "Coton de Tulear",
            "Dalmatian",
            "Doberman Pinscher",
            "Dogo Argentino",
            "Dutch Shepherd",
            "English Setter",
            "English Shepherd",
            "English Springer Spaniel",
            "English Toy Spaniel",
            "English Toy Terrier",
            "Eurasier",
            "Field Spaniel",
            "Finnish Lapphund",
            "Finnish Spitz",
            "French Bulldog",
            "German Pinscher",
            "German Shepherd Dog",
            "German Shorthaired Pointer",
            "Giant Schnauzer",
            "Glen of Imaal Terrier",
            "Golden Retriever",
            "Gordon Setter",
            "Great Dane",
            "Great Pyrenees",
            "Greyhound",
            "Griffon Bruxellois",
            "Harrier",
            "Havanese",
            "Irish Setter",
            "Irish Terrier",
            "Irish Wolfhound",
            "Italian Greyhound",
            "Japanese Chin",
            "Japanese Spitz",
            "Keeshond",
            "Komondor",
            "Kooikerhondje",
            "Kuvasz",
            "Labrador Retriever",
            "Lagotto Romagnolo",
            "Lancashire Heeler",
            "Leonberger",
            "Lhasa Apso",
            "Maltese",
            "Miniature American Shepherd",
            "Miniature Pinscher",
            "Miniature Schnauzer",
            "Ratting",
            "Newfoundland",
            "Norfolk Terrier",
            "Norwich Terrier",
            "Nova Scotia Duck Tolling Retriever",
            "Old English Sheepdog",
            "Olde English Bulldogge",
            "Papillon",
            "Pekingese",
            "Pembroke Welsh Corgi",
            "Perro de Presa Canario",
            "Pharaoh Hound",
            "Plott",
            "Pomeranian",
            "Poodle (Miniature)",
            "Poodle (Toy)",
            "Pug",
            "Puli",
            "Pumi",
            "Rat Terrier",
            "Redbone Coonhound",
            "Rhodesian Ridgeback",
            "Rottweiler",
            "Russian Toy",
            "Saint Bernard",
            "Saluki",
            "Samoyed",
            "Schipperke",
            "Scottish Deerhound",
            "Scottish Terrier",
            "Shetland Sheepdog",
            "Shiba Inu",
            "Shih Tzu",
            "Shiloh Shepherd",
            "Siberian Husky",
            "Silky Terrier",
            "Smooth Fox Terrier",
            "Soft Coated Wheaten Terrier",
            "Spanish Water Dog",
            "Spinone Italiano",
            "Staffordshire Bull Terrier",
            "Standard Schnauzer",
            "Swedish Vallhund",
            "Thai Ridgeback",
            "Tibetan Mastiff",
            "Tibetan Spaniel",
            "Tibetan Terrier",
            "Toy Fox Terrier",
            "Treeing Walker Coonhound",
            "Vizsla",
            "Weimaraner",
            "Welsh Springer Spaniel",
            "West Highland White Terrier",
            "Whippet",
            "White Shepherd",
            "Wire Fox Terrier",
            "Wirehaired Pointing Griffon",
            "Wirehaired Vizsla",
            "Xoloitzcuintli",
            "Yorkshire Terrier"

    };



    private static HashMap<Integer,String> hasMapRecordList=new HashMap<Integer,String>();
    public static HashMap<Integer, String> getMaprecordList() {
        hasMapRecordList.clear();
        hasMapRecordList.put( 4,"Neo");
        hasMapRecordList.put( 2,"Tinity");
        hasMapRecordList.put( 3,"Morfeo");
        hasMapRecordList.put( 1,"Cifra");
        hasMapRecordList.put( 3,"Smith agent");
        hasMapRecordList.put( 5,"Oraculo");
        hasMapRecordList.put( 6,"Merovingio");
        hasMapRecordList.put( 2,"Persephone");
        hasMapRecordList.put( 1,"Arquitect");
        hasMapRecordList.put( 4,"Anakin Skywalker");
        hasMapRecordList.put( 5,"Luke Skywalker");
        hasMapRecordList.put( 6,"Han solo");
        hasMapRecordList.put( 8,"Leia Organa");
        hasMapRecordList.put( 11,"Yoda");
        hasMapRecordList.put( 24,"Chewbacca");

        return hasMapRecordList;
    };

    public static DogResponse dogResponse;
    public static Cat cat;
    public static List<CatListResponse> listAllcats;
    public static List<DogResponse> listAllDogs;

}
