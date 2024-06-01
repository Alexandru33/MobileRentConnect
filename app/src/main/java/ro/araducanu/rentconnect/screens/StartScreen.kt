package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    val backgroundImage = painterResource(R.drawable.logo_color)

    Box(modifier = modifier.fillMaxSize()){

        //background image
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.None,
            alignment = Alignment.Center
        )


        //Login and Register buttons
        Column(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(0.dp, 0.dp, 0.dp, 20.dp)){
            Button(
                onClick = { RentConnectAppRouter.navigateTo(Screen.LoginScreen) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(40))
                    .shadow(15.dp),
                colors = ButtonDefaults.buttonColors(PrimaryBlue)
            ) {
                Text(text = stringResource(R.string.login), color = Color.White, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal))

            }
            Button(
                onClick = { RentConnectAppRouter.navigateTo(Screen.SignUpScreen) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(15.dp)
                    .border(1.dp, PrimaryBlue, RoundedCornerShape(30)),
                shape = RoundedCornerShape(40),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = stringResource(R.string.sign_up), color = PrimaryBlue, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium))
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview() {

    StartScreen(modifier = Modifier.fillMaxSize())
}