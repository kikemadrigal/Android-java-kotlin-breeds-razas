package es.tipolisto.breeds.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import java.net.InetAddress;

public class Util {
    public static final int MY_PERMISSIONS_REQUEST_WIFI_STATUS=100;
    //Esta conectado a la red de casa u oficina?
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //Está conectado a internet
    public static boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }

    //Tiene los permisos en el manifest?
    public static boolean checkPermissionsInternet(Activity activity) {
        int permissionCheckAccessWifiSTate = ContextCompat.checkSelfPermission(activity.getApplicationContext(),
                Manifest.permission.ACCESS_WIFI_STATE);
        int permissionCheckFineLocation = ContextCompat.checkSelfPermission(activity.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        //Si el permiso es diferente a  permiso concedido
        if(permissionCheckAccessWifiSTate!= PackageManager.PERMISSION_GRANTED || permissionCheckFineLocation!=PackageManager.PERMISSION_GRANTED){
            //Log.d("Mensaje", "Oncreate dice: No tienes los permisos" +permissionCheckAccessWifiSTate+"--"+PackageManager.PERMISSION_GRANTED+", accessfinelocation: "+permissionCheckFineLocation);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_WIFI_STATUS);
            }
            return false;
        }else{
            return true;
        }
    }

}
