package es.tipolisto.breeds.ui.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import es.tipolisto.breeds.R;
import es.tipolisto.breeds.data.database.AppDatabase;
import es.tipolisto.breeds.data.database.RecordDao;
import es.tipolisto.breeds.data.model.RecordEntity;
import es.tipolisto.breeds.data.preferences.PreferencesManagaer;
import es.tipolisto.breeds.ui.MainActivity;

public class Dialog {
    public static void showSialogPresentation(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage(R.string.presentation);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.cancel();
            //exitToMenu(activity);
            Intent intent=new Intent(activity.getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
            activity.finish();
        });
        builder.create().show();
    }
    public static void showSialogExit(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage(activity.getApplicationContext().getString(R.string.exit_lost_score));
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.cancel();
            exitToMenu(activity);
        });
        builder.create().show();
    }


    public static void showDialogNewRecord(Activity activity, int score){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.message);
        builder.setMessage(activity.getApplicationContext().getString(R.string.new_record_message)+String.valueOf(score));
        final EditText editNameRecord=new EditText(activity.getApplicationContext());
        editNameRecord.setHint(R.string.Introduce_your_name);

        builder.setView(editNameRecord);
        builder.setOnDismissListener(dialogInterface -> showDialogNewRecord(activity,score));
        builder.setPositiveButton("Ok", (dialog, id) -> {
            //Comprobamos que el nombre existe
            RecordEntity existName;
            AppDatabase db = Room.databaseBuilder(activity.getApplicationContext(), AppDatabase.class, "database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            RecordDao recordDao= db.recordDao();
            String name=editNameRecord.getText().toString();
            existName=recordDao.getNameRecord(editNameRecord.getText().toString());
            if (name.equals("") || name.length()==0){
                Toast.makeText(activity, R.string.write_a_name, Toast.LENGTH_SHORT).show();
                showDialogNewRecord(activity,score);
            }else {
                if (existName!=null){
                    Toast.makeText(activity, R.string.write_another_name, Toast.LENGTH_SHORT).show();
                    editNameRecord.setText(" ");
                    showDialogNewRecord(activity,score);
                }else{
                    PreferencesManagaer preferencesManagaer=new PreferencesManagaer(activity.getApplicationContext());
                    preferencesManagaer.saveNameNewRecord(name);
                    preferencesManagaer.saveNewRecord(score);
                    dialog.cancel();
                    exitToMenu(activity);
                }
            }

        });
        builder.create().show();
    }


    public static void showDialogGameOver(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage("          Game over");
        builder.setOnDismissListener(dialogInterface -> {
            dialogInterface.cancel();
            exitToMenu(activity);
        });
        android.app.Dialog dialog=builder.create();
        dialog.show();
    }
    public static void showDialogNecessaryInternet(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage(R.string.necessary_internet);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.cancel();
            activity.finish();
        });
        android.app.Dialog dialog=builder.create();
        dialog.show();
    }
    public static void showDialogProblemGetDataInternet(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Breeds says");
        builder.setMessage(R.string.problem_data_internet);
        builder.setPositiveButton("Ok", (dialog, id) -> {
            dialog.cancel();
            activity.finish();
        });
        android.app.Dialog dialog=builder.create();
        dialog.show();
    }




    private static void exitToMenu(Activity activity){
        Intent intent=new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        /*Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(a);*/
        activity.finish();
    }


}
