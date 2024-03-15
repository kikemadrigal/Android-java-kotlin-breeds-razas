package es.tipolisto.breeds.ui.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import es.tipolisto.breeds.R

@Composable
fun MyToast(value:String){
    Toast.makeText(LocalContext.current,value, Toast.LENGTH_LONG)
        .show()
}