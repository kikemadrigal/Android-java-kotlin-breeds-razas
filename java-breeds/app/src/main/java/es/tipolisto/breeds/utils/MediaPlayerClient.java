package es.tipolisto.breeds.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;


import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.PreferencesManagaer;

public class MediaPlayerClient {
    //private static MediaPlayerClient instance;
    private MediaPlayer mediaPlayer;
    private Context context;
    private PreferencesManagaer preferencesManagaer;
    public MediaPlayerClient(Context context){
        mediaPlayer=new MediaPlayer();
        this.context=context;
        preferencesManagaer=new PreferencesManagaer(context);
    }


    /*public static MediaPlayerClient getInstance(){
        if(instance==null){
            instance=new MediaPlayerClient();
        }
        return instance;
    }*/

    public void playIntro(){
        if (!preferencesManagaer.getMusicOnOff()) return;
        mediaPlayer = MediaPlayer.create(context, R.raw.intro);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

    }
    public void playInGame(){
        if (!preferencesManagaer.getMusicOnOff()) return;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.ingame);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
    /*
    public void playGameOver(Context context){
        mediaPlayer = MediaPlayer.create(context, R.raw.gameover);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
    }*/

    public void playSound(String sound){
        if (!preferencesManagaer.getMusicOnOff()) return;
        mediaPlayer.stop();
        mediaPlayer.release();
        if (sound.equals("button")) mediaPlayer = MediaPlayer.create(context, R.raw.clickbutton);
        if (sound.equals("success")) mediaPlayer = MediaPlayer.create(context, R.raw.success);
        if (sound.equals("failure")) mediaPlayer = MediaPlayer.create(context, R.raw.failure);
        mediaPlayer.start();
    }
    public void releaseSound(){
        if (!preferencesManagaer.getMusicOnOff()) return;
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
