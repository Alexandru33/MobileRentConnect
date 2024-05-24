package ro.araducanu.rentconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ro.araducanu.rentconnect.app.RentConnectApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentConnectApp()
        }
    }
}

@Composable
@Preview
fun DefaultPreview()
{
    RentConnectApp()
}