package es.tipolisto.breeds.ui.views.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.tipolisto.breeds.R
import es.tipolisto.breeds.data.preferences.PreferenceManager
import es.tipolisto.breeds.ui.theme.BreedsTheme
import es.tipolisto.breeds.ui.viewModels.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navController: NavController){
    val context= LocalContext.current
    var isDarkMode by remember { mutableStateOf(PreferenceManager.readPreferenceThemeDarkOnOff(context)) }
    BreedsTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = "Records")
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
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Login(loginViewModel)
            }
        }//Final del scofold
    }//Final de breedsTheme
}

@Composable
fun Login(loginViewModel: LoginViewModel){
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val loginEnable: Boolean by loginViewModel.loginEnable.observeAsState(initial = false)


    Column (modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        HeadImage(Modifier.align(Alignment.CenterHorizontally))
       //Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email, {loginViewModel.onLoginChange(it,password)})
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField(password,{loginViewModel.onLoginChange(email,it)})
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword()
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(loginEnable)
            {
                loginViewModel.onLoginSelected()
            }

    }
}

@Composable
fun LoginButton(loginEnabled:Boolean, onLoginSelected:()->Unit) {
    Button(
        onClick = { onLoginSelected },
        modifier= Modifier
            .fillMaxWidth()
            .height(40.dp),
        colors = ButtonDefaults.buttonColors(),
        enabled = loginEnabled
    ) {
        Text(text = "Iniciar sesion")
    }
}

@Composable
fun ForgotPassword() {
    Text(text = "Olvidaste la contraseña", modifier=Modifier.clickable {  })
}

@Composable
fun HeadImage(modifier: Modifier){
    Image(modifier=modifier.height(400.dp),painter = painterResource(id = R.drawable.login), contentDescription = "Header")
}


@Composable
fun EmailField(email:String, onTextFielChange:(String)->Unit){
    TextField(
        value = email,
        onValueChange ={onTextFielChange(it)},
        modifier=Modifier.fillMaxWidth(),
        placeholder = { Text(text="eamil") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun PasswordField(password:String, onTextFielChange: (String) -> Unit){
    TextField(
        value = password,
        onValueChange = {onTextFielChange(it)},
        placeholder = { Text(text = "Password")},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}