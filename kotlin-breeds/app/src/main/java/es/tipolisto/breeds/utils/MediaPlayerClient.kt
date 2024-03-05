package es.tipolisto.breeds.utils

import android.content.Context
import android.media.MediaPlayer
import es.tipolisto.breeds.R


class MediaPlayerClient(val context: Context) {
    private var inGameMusic:MediaPlayer
    private var menuMusic:MediaPlayer
    private var endMusic:MediaPlayer
    private var effects:MediaPlayer
    private var instance: MediaPlayerClient? = null
    init {
        inGameMusic = MediaPlayer.create(context, R.raw.ingame)
        menuMusic= MediaPlayer.create(context, R.raw.intro)
        endMusic= MediaPlayer.create(context, R.raw.gameover)
        effects= MediaPlayer.create(context, R.raw.clickbutton)
        inGameMusic.isLooping=true
    }
    fun getInstance(): MediaPlayerClient {
        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = MediaPlayerClient(context)
                }
            }
        }
        return instance!!
    }
    fun playInGameMusic(){
        if(!inGameMusic.isPlaying)inGameMusic.start()
    }

    fun stopInGameMusic(){
        if(inGameMusic.isPlaying)
            inGameMusic.stop()
    }

    fun playMenuMusic(){
        if(!inGameMusic.isPlaying)menuMusic.start()
    }

    fun stopMenuMusic(){
        if(inGameMusic.isPlaying)
            menuMusic.stop()
    }
    fun clickAudio(){
        effects.start()
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

    fun playSound(effectsType: AudioEffectsType, context: Context?){
        var effects:MediaPlayer
        when(effectsType.name){
            "click"-> effects=MediaPlayer.create(context, R.raw.clickbutton)
            "success"-> effects = MediaPlayer.create(context, R.raw.success)
            "failure"-> effects = MediaPlayer.create(context, R.raw.failure)
            else->{
                effects = MediaPlayer.create(context, R.raw.failure)
            }
        }
        effects.start()
    }
}


