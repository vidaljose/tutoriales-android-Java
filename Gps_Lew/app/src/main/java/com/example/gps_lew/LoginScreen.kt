package com.example.gps_lew

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException



@Composable
fun LoginScreen(navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(
            modifier = Modifier.align(Alignment.Center),
            navController=navController,
            coroutineScope = coroutineScope)
        Spacer(modifier = Modifier.padding(16.dp))

    }

}

@Composable
fun Login(modifier: Modifier,navController: NavHostController, coroutineScope: CoroutineScope) {

    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var isLoading: Boolean by remember { mutableStateOf(false) }

    val sharedPrefUtil = SharedPrefUtil(LocalContext.current)
    /*
    if(sharedPrefUtil.getData("user") != null){
        val emailData = sharedPrefUtil.getData("user")
        navController.navigate("Details/$emailData")
    }
    */

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier= Modifier.align(Alignment.Center))
        }
    }else{
        Column(modifier = modifier) {
            HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier =Modifier.height(30.dp))
            EmailField(email, { email = it})
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password, { password = it })
            Spacer(modifier = Modifier.padding(8.dp))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                loginEnable=(email.length > 0 && password.length > 0))
            {
                // navController.navigate("Details/$email")
                coroutineScope.launch {
                    isLoading = true
                    if(sendDataLogin(email, password)){
                        sharedPrefUtil.saveData("user", email) //
                        navController.navigate("Details/$email")
                    }
                    isLoading = false
                }
            }
        }
    }



}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.gps_image),
        contentDescription = "Imagen anime",
        modifier = modifier
    )
}


@Composable
fun EmailField(email: String, onTextFieldChange: (String) -> Unit) {

    TextField(
        value = email,
        onValueChange = { onTextFieldChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Usuario")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,

    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Contraseña")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,

    )
}



@Composable
fun LoginButton(modifier: Modifier, loginEnable:Boolean, onLoginSelected:()->Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier.fillMaxWidth(),
        enabled = loginEnable
    ) {
        Text(text = "Ingresar")
    }
}

suspend fun sendDataLogin(usuario: String, pass: String): Boolean {
    val url = "http://190.246.216.236:3033/login"
    val json = "{\"user\": \"${usuario}\", \"password\": \"${pass}\"}"
    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = json.toRequestBody(mediaType)

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    return try {
        val response = withContext(Dispatchers.IO) {
            OkHttpClient().newCall(request).execute()
        }

        response.use {
            if (response.isSuccessful) {
                // Operación exitosa

                true
            } else {
                // Mostrar un mensaje de error al usuario si es necesario
                false
            }
        }
    } catch (e: IOException) {
        // Manejar la excepción y mostrar un mensaje de error si es necesario
        false
    }
}

