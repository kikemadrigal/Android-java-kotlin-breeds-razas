package es.tipolisto.breeds.utils

import java.text.SimpleDateFormat
import java.util.Date

class Util {
    companion object{
        fun trimString(value:String):String{
            return if(value.length>15) value.substring(0,15)+"..." else value
        }

        fun getDate():String{
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            return sdf.format(Date())
        }
    }
}