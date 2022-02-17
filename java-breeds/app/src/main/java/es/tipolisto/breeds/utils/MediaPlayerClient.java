package es.tipolisto.breeds.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;


import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.preferences.PreferencesManagaer;


public class MediaPlayerClient {
    //La otra clase para reproducir audi se llama AudioTrack: https://developer.android.com/reference/android/media/AudioTrack?hl=es-419
    private MediaPlayer mediaPlayer;
    private Context context;
    private PreferencesManagaer preferencesManagaer;

    public MediaPlayerClient(Context context){
        mediaPlayer=new MediaPlayer();
        this.context=context;
        preferencesManagaer=new PreferencesManagaer(context);
        //Es posible crear un efecto fade in y fade out con VolumeShaper pero el dispositivo tiene que ser mayor de android 8.0 api 26
        /*if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VolumeShaper.Configuration config =
                    new VolumeShaper.Configuration.Builder()
                            .setDuration(3000)
                            .setCurve(new float[] {0.f, 1.f}, new float[] {0.f, 1.f})
                            .setInterpolatorType(VolumeShaper.Configuration.INTERPOLATOR_TYPE_STEP)
                            .build();
            VolumeShaper volumeShaper= mediaPlayer.createVolumeShaper(config);;
            mediaPlayer.setNextMediaPlayer(mediaPlayer);
        }*/

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
