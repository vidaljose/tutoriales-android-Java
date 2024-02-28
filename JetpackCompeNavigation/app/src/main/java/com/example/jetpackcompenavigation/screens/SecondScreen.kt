package com.example.jetpackcompenavigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SecondScreen(){
    Scaffold{
        BodyContent2()
    }
}

@Composable
fun BodyContent2(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hola navegacion")
        Button(onClick = { /*TODO*/ }) {
            Text("Navega")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2(){
    SecondScreen()
}