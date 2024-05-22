package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.components.HeadingTextComponent
import ro.araducanu.rentconnect.components.NormalTextComponent

@Composable
fun SignUpScreen() {

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
            .background(Color.White)
    ) {
        Column(  modifier = Modifier.fillMaxSize() ){
            NormalTextComponent(
                value = stringResource(id = R.string.welcome_message)
            )
            HeadingTextComponent(value = stringResource(R.string.create_account))
        }

    }

}

@Preview(showBackground = true , showSystemUi = true, name = "Preview")
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}