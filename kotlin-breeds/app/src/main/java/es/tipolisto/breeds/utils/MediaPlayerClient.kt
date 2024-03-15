package es.tipolisto.breeds.utils

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import es.tipolisto.breeds.R


class MediaPlayerClient (private val context: Context) {
    private var inGameMusic:MediaPlayer
    private var menuMusic:MediaPlayer
    private var endMusic:MediaPlayer
    private var effects:MediaPlayer
    init {
        inGameMusic = MediaPlayer.create(context, R.raw.ingame)
        menuMusic= MediaPlayer.create(context, R.raw.intro)
        endMusic= MediaPlayer.create(context, R.raw.gameover)
        effects= MediaPlayer.create(context, R.raw.clickbutton)
        inGameMusic.isLooping=true
        menuMusic.isLooping=true
    }
    fun playSound(effectsType: AudioEffectsType){
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
    /*fun getInstance(): MediaPlayerClient {
        if (instance == null) {
            synchronized(this) {
                if (instance == null) {
                    instance = MediaPlayerClient(context)
                }
            }
        }
        return instance!!
    }*/
    fun playInGameMusic(){
        inGameMusic = MediaPlayer.create(context, R.raw.ingame)
        if(!inGameMusic.isPlaying)inGameMusic.start()
    }

    fun stopInGameMusic(){
        if(inGameMusic.isPlaying)inGameMusic.stop()
    }

    fun playMenuMusic(){
        menuMusic= MediaPlayer.create(context, R.raw.intro)
        //Log.d("TAG","MediaPlayerClient dice: Hemos entrado en reproducir musica del menu")
        //menuMusic= MediaPlayer.create(context, R.raw.intro)
        if(!menuMusic.isPlaying){
            //menuMusic.reset()
            menuMusic.start()
            //Log.d("TAG","MediaPlayerClient dice: reproduciendo musica menu")
        }
    }

    fun stopMenuMusic(){
        //Log.d("TAG","MediaPlayerClient dice: Hemos entrado en parar musica menu")
        if(menuMusic.isPlaying){
            menuMusic.stop()
            //Log.d("TAG","MediaPlayerClient dice: musica menu parada")
        }
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


}


