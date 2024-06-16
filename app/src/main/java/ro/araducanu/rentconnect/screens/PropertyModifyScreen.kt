package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ro.araducanu.rentconnect.components.ImageCarousel
import ro.araducanu.rentconnect.components.PropertyEditLongTextCard
import ro.araducanu.rentconnect.components.PropertyLongTextCard
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White

@Composable
fun PropertyModifyScreen(
    propertyViewModel : PropertyViewModel = viewModel()
) {
    val selectedProperty = propertyViewModel.selectedProperty

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            //.padding(28.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.verticalScroll(rememberScrollState())
        ) {
            ImageCarousel(PropertyImageViewModel(), selectedProperty.value?.id!!)
            PropertyEditLongTextCard()
            Spacer(modifier = Modifier.weight(1f))
            //BottomButtonsDetailsPropertyComponent(propertyLong = propertyLong)
            //PropertyLongTextCard(propertyLong = propertyLongDummy)
            //Spacer(modifier = Modifier.weight(1f))


        }
    }
}