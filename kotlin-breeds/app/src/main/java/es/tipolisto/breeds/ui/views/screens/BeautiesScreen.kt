package es.tipolisto.breeds.ui.views.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage

import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.utils.Constants.Companion.CAMERAX_PERMISSIONS


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeautiesScreen(navController: NavController){
    /*if(!hasREquiredPermission()){
        ActivityCompat.requestPermissions(
            LocalContext.current, CAMERAX_PERMISSIONS,0
        )
    }*/

    val context= LocalContext.current
    var isDarkMode by remember {mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context))}

    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Beauty ranking",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                )
            }
        ) {
            //Spacer(modifier = Modifier.padding(it))
            Beauty(it)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Beauty(it: PaddingValues) {
    //var email by remember {mutableStateOf("")}
    var name by remember {mutableStateOf("")}
    var selectedImageUri by remember { mutableStateOf<Uri?>(null)  }
    val singlePhotoPickerLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri->selectedImageUri=uri}
    )
    Column(
        modifier = Modifier.padding(it),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedImageUri==null){
            Image(
                modifier = Modifier.height(300.dp),
                painter = painterResource(id = R.drawable.photo_dog),
                contentDescription = "Photo animal"
            )
        }else{
            AsyncImage(
                model = selectedImageUri,
                contentDescription = null,
                modifier= Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
                )
        }
        Row (modifier=Modifier.fillMaxWidth().background(Color.Yellow)){
            Button(
                onClick = { singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                ) },
                modifier=Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(),
                enabled = true
            ) {
                Text(text = "Poner foto")
            }
            Button(
                onClick = { singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                ) },
                modifier=Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(),
                enabled = true
            ) {
                Text(text = "Cámara")
            }

        }

        Text(text = "Yo soy", style = MaterialTheme.typography.headlineLarge)
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Name") },
            //Style = MaterialTheme.colorScheme.secondary,
            //colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.background),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Text(
            text = "Y soy el más guapo del mundo",
            style = MaterialTheme.typography.bodyMedium
        )
    }

}//Final de la funcion

@Preview(showBackground = true)
@Composable
fun BeautyPreview(){
    //Beauty()
}


@Composable
fun hasREquiredPermission():Boolean{
    return CAMERAX_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            LocalContext.current,
            it,
            ) == PackageManager.PERMISSION_GRANTED
    }
}









