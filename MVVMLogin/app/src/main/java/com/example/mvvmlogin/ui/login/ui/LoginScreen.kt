package com.example.mvvmlogin.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mvvmlogin.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(modifier = Modifier.align(Alignment.Center), viewModel)
        Spacer(modifier = Modifier.padding(16.dp))

    }

}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel) {

    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(false)
    val coroutineScope = rememberCoroutineScope()

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
        }
    }else{
        Column(modifier = modifier) {
            HeaderImage(modifier = Modifier.align(Alignment.CenterHorizontally))
            EmailField(email, { viewModel.onLoginChange(it,password) })
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(password, { viewModel.onLoginChange(email,it) })
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(modifier = Modifier.align(Alignment.End))
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(modifier = Modifier.align(Alignment.CenterHorizontally),loginEnable=loginEnable)
            {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
        }
    }



}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.anime),
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
            Text(text = "Email")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            background = Color.White,
        )

    )
}

@Composable
fun PasswordField(password: String, onTextFieldChange: (String) -> Unit) {
    TextField(
        value = password,
        onValueChange = {onTextFieldChange(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        textStyle = TextStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            background = Color.White,
        )

    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Olvidaste el password? ",
        modifier = modifier,
        color = Color.Blue,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
}

@Composable
fun LoginButton(modifier: Modifier,loginEnable:Boolean,onLoginSelected:()->Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier.fillMaxWidth(),
        enabled = loginEnable
    ) {
        Text(text = "Ingresar")
    }
}
