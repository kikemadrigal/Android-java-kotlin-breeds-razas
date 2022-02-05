package es.tipolisto.breeds.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Util {
    public static void crearAlertDialog(final Context context, final String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Mensaje");
        builder.setMessage(mensaje);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
