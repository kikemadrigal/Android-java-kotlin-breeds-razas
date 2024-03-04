package es.tipolisto.breeds.utils

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager

class MediaPlayerClient {


    companion object{
        private var instance: MediaPlayerClient? = null
        var contextFromClient:Context?=null
        var mediaPlayer:MediaPlayer?=MediaPlayer.create(contextFromClient,R.raw.intro)

        fun getInstance(context: Context): MediaPlayerClient {
            this.contextFromClient=context
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MediaPlayerClient()
                    }
                }
            }
            return instance!!
        }
    }
    /*fun updateAudio() {
        var musicOnOff= PreferenceManager.readPreferenceMusicOnOff(contextFromClient!!)
        //val mediaPlayer=MediaPlayer.create(context,R.raw.intro)
        if(musicOnOff){
            if(!mediaPlayer!!.isPlaying)mediaPlayer!!.start()
            else
                if(mediaPlayer!!.isPlaying)mediaPlayer!!.stop()
        }
    }*/
}