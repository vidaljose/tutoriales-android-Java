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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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

@Preview(showSystemUi = true)
@Composable
fun LoginScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Login(modifier = Modifier.align(Alignment.Center))
        Spacer(modifier = Modifier.padding(16.dp))

    }

}

@Composable
fun Login(modifier: Modifier){
    Column(modifier = modifier) {
        HeaderImage(modifier=Modifier.align(Alignment.CenterHorizontally))
        EmailField()
        Spacer(modifier = Modifier.padding(4.dp))
        Password()
        Spacer(modifier = Modifier.padding(8.dp))
        ForgotPassword(modifier=Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.padding(16.dp))
        LoginButton(modifier=Modifier.align(Alignment.CenterHorizontally))
    }

}

@Composable
fun HeaderImage(modifier: Modifier){
    Image(
        painter = painterResource(id = R.drawable.anime),
        contentDescription = "Imagen anime",
        modifier=modifier
    )
}

@Preview
@Composable
fun EmailField(){
     TextField(
         value = "",
         onValueChange = {},
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
fun Password(){
    TextField(
        value = "",
        onValueChange = {},
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
fun ForgotPassword(modifier: Modifier){
    Text(
        text = "Olvidaste el password? ",
        modifier = modifier,
        color = Color.Blue,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )
}

@Composable
fun LoginButton(modifier: Modifier){
    Button(
        onClick = { /*TODO*/ },
        modifier=Modifier.fillMaxWidth()

    ) {
            Text(text = "Ingresar")
    }
}
