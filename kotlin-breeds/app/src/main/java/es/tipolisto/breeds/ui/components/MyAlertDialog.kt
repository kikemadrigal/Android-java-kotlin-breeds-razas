package es.tipolisto.breeds.ui.components

import android.app.Dialog
import android.content.Context
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import es.tipolisto.breeds.R
import es.tipolisto.breeds.ui.navigation.AppScreens


fun onBackPressed(navController: NavController, context: Context) {
    //quizListener.playButton("click")
    val builder= android.app.AlertDialog.Builder (context)
    builder.setTitle(context.getString(R.string.breeds_says))
    builder.setMessage(context.getString(R.string.are_you_sure_you_want_to_go_out))
    builder.setPositiveButton("Yes") { dialog, id ->
        dialog.cancel()
        navController.popBackStack()
        navController.navigate(AppScreens.MenuScreen.route)
    }
    val dialog: Dialog = builder.create()
    dialog.show()
}
