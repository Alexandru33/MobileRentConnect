package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.araducanu.rentconnect.components.MySearchBarComponent

@Composable
fun SearchScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            //.padding(28.dp)
            .background(Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            //MySearchBarComponent()

        }
    }
}

@Composable
@Preview
fun SearchScreenPreview() {
    SearchScreen()
}