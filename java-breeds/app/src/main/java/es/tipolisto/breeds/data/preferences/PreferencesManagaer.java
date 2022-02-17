package es.tipolisto.breeds.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManagaer {
    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    public PreferencesManagaer(Context context){
        this.context=context;
        this.sharedPref = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        this.editor = sharedPref.edit();
    }
    public void deletePreferences(){
        editor.clear();
        editor.commit();
    }
    public int getHighRecord(){
        return sharedPref.getInt("highscore",0);
    }
    public void saveNewRecord(int score){
        editor.putInt("highscore", score);
        editor.commit();
    }
    public String getnameRecord(){
        return sharedPref.getString("namescore","Nameless");
    }
    public void saveNameNewRecord(String name){
        editor.putString("namescore", name);
        editor.commit();
    }
    public void saveMusicOnOff(boolean musicOnOff){
        editor.putBoolean("musicOnOff", musicOnOff);
        editor.commit();
    }
    public boolean getMusicOnOff(){
        return sharedPref.getBoolean("musicOnOff",false);
    }

}
