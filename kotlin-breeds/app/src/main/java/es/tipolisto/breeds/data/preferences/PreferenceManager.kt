package es.tipolisto.breeds.data.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager {
    companion object{
        private lateinit var sharedPref: SharedPreferences
        const val PREFS_NAME="sharedPref"
        const val MUSICONOFF = false
        const val THEMEDARKONOFF = false
        fun savePreferenceMusicOnOff(context:Context,value:Boolean){
            sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putBoolean("musicOnOff", value)
                apply()
            }
        }

        fun readPreferenceMusicOnOff(context:Context):Boolean{
            sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            return sharedPref.getBoolean("musicOnOff",false)
        }

        fun savePreferenceThemeDarkOnOff(context:Context,value:Boolean){
            sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putBoolean("ThemeDarkOnOff", value)
                apply()
            }
        }

        fun readPreferenceThemeDarkOnOff(context: Context):Boolean{
            sharedPref = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
            return sharedPref.getBoolean("ThemeDarkOnOff",false)
        }

    }




}