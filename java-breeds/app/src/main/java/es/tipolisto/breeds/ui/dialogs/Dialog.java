package es.tipolisto.breeds.ui.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.preferences.PreferencesManagaer;
import es.tipolisto.breeds.ui.MainActivity;

public class Dialog {
    public static void showSialogExit(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Mensaje");
        builder.setMessage(activity.getApplicationContext().getString(R.string.exit_lost_score));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                exitToMenu(activity);
            }
        });
        builder.create().show();
    }

    public static void showDialogNewRecord(Activity activity, int score){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.message);
        builder.setMessage(activity.getApplicationContext().getString(R.string.new_record_message)+String.valueOf(score));
        EditText editNameRecord=new EditText(activity.getApplicationContext());
        editNameRecord.setHint(R.string.Introduce_your_name);

        builder.setView(editNameRecord);
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.cancel();
                exitToMenu(activity);;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                String name=editNameRecord.getText().toString();
                if (!name.equals("") && name.length()>0){
                    PreferencesManagaer preferencesManagaer=new PreferencesManagaer(activity.getApplicationContext());
                    preferencesManagaer.saveNameNewRecord(name);
                    //En el recordFragment comprobamos si su record debe de estar en la tabla de records
                }

                exitToMenu(activity);
            }
        });
        builder.create().show();
    }


    public static void showDialogGameOver(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage("          Game over");
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.cancel();
                exitToMenu(activity);
            }
        });
        android.app.Dialog dialog=builder.create();
        dialog.show();
    }
    public static void showDialogNecessaryInternet(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage(R.string.necessary_internet);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
                activity.finish();
            }
        });
        /*builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogInterface.cancel();
                //exitToMenu(activity);
                activity.finish();
            }
        });*/
        android.app.Dialog dialog=builder.create();
        dialog.show();
    }




    private static void exitToMenu(Activity activity){
        Intent intent=new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.finish();
    }


}
