@file:OptIn(ExperimentalMaterial3Api::class)

package es.tipolisto.breeds.ui.views.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import es.tipolisto.breeds.data.database.AppDataBase
import es.tipolisto.breeds.data.database.records.RecordEntity
import es.tipolisto.breeds.data.repositories.RecordsRepository
import es.tipolisto.breeds.ui.theme.BreedsTheme
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.coroutineContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController:NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Settings", color= Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon={
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(imageVector = Icons.Default.ArrowBack,contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) {
        val context = LocalContext.current
        var musicIsChecked by remember { mutableStateOf(false)}
        val iconsMusicIsChecked=if(musicIsChecked) Icons.Filled.Check else Icons.Filled.Close
        var ThemeDarkIsChecked by remember { mutableStateOf(false)}
        val iconsThemeDarkIsChecked=if(musicIsChecked) Icons.Filled.Check else Icons.Filled.Close
        Spacer(modifier = Modifier.size(10.dp))
        Column(
                modifier = Modifier.padding(it).fillMaxSize().background(Color.Red)
                ){
            Row(
                modifier = Modifier.background(Color.Yellow).padding(10.dp,10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text(text = "Music on / off")
                Switch(
                    checked = false,
                    onCheckedChange = {
                        musicIsChecked=it
                        Toast.makeText(context, "ha cambiado la musica",Toast.LENGTH_LONG).show()
                    },
                    thumbContent = {
                        Icon(
                            imageVector=iconsMusicIsChecked,
                            contentDescription = "Music On / Off",
                            modifier=Modifier.size(20.dp)
                        )
                    }
                )
            }
            Row (
                modifier = Modifier.background(Color.Yellow).padding(10.dp,10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Text(text = "Theme dark On / Off")
                Switch(
                    checked = false,
                    onCheckedChange = {
                        ThemeDarkIsChecked=it
                        Toast.makeText(context, "ha cambiado el teme dark",Toast.LENGTH_LONG).show()
                    },
                    thumbContent = {
                        Icon(
                            imageVector=iconsThemeDarkIsChecked,
                            contentDescription = "Theme dark On / Off",
                            modifier=Modifier.size(20.dp)
                        )
                    }
                )
            }

            val webAnnotatedString = buildAnnotatedString {
                append("Web: ")
                pushStringAnnotation(tag = "web", annotation = "https://breeds.tipolisto.es")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("breeds.tipolisto.es")
                }
                pop()
            }
            val contactAnnotatedString = buildAnnotatedString {
                append("Contact ")
                pushStringAnnotation(tag = "contact", annotation = "email://adm.tipolisto.es")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("email://adm.tipolisto.es")
                }
                append(" \\n")
                pop()
            }
            val termsAnnotatedString = buildAnnotatedString {
                pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Terms of use")
                }
                pop()
            }

            ClickableText(text = webAnnotatedString, style = MaterialTheme.typography.bodyLarge, onClick = { offset ->
                webAnnotatedString.getStringAnnotations(tag = "web", start = offset, end = offset)
            })
            ClickableText(text = contactAnnotatedString, style = MaterialTheme.typography.bodyLarge, onClick = { offset ->
                contactAnnotatedString.getStringAnnotations(
                    tag = "contact",
                    start = offset,
                    end = offset
                )
            })
            ClickableText(text = termsAnnotatedString, style = MaterialTheme.typography.bodyLarge, onClick = { offset ->
                termsAnnotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)/*.firstOrNull()?.let {
                    Log.d("terms URL", it.item)
                }*/
            })

            Text(text = "Elige si quieres adivinar la raza del gato o del perro.\nTienes 7 vidas.\nElige configuración para cambiar las carácteristicas del juego.")
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    BreedsTheme {
        //SettingsScreen()
    }
}





