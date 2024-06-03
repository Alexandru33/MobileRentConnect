package ro.araducanu.rentconnect.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonColors
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Elevator
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import ro.araducanu.rentconnect.R
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.data.models.propertyLongDummy
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import com.google.accompanist.pager.rememberPagerState
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.GrayIcons
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageCarousel() {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        R.drawable.apartament_image,
        R.drawable.apartament_image,
        R.drawable.apartament_image

    )

    // Oferă o listă de painteri care sunt memorate pentru a preveni recrearea lor la fiecare reconstruire.
    val painters = imageSlider.map { painterResource(id = it) }

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(shape = RoundedCornerShape(0, 0, 10, 10))
        ) {
            HorizontalPager(
                count = painters.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Image(
                    painter = painters[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Button(onClick = { RentConnectAppRouter.navigateBack()},
                colors = ButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
                modifier = Modifier.padding(top = 5.dp)){
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White, modifier = Modifier.background(
                    Color.Gray, RoundedCornerShape(10)
                ))
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                activeColor = Color.White,
                inactiveColor = Color.Gray
            )
        }
    }
}

@Composable
fun PropertyLongTextCard(
    propertyLong: PropertyLong
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .verticalScroll(scrollState)


    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = propertyLong.title,
                style = MaterialTheme.typography.h5,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            shape = RoundedCornerShape(10),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
                pressedElevation = 6.dp
            ),
            colors = CardColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.Black
            )
        ) {
            androidx.compose.material3.Text(
                text = propertyLong.description,
                style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight(400)),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(10.dp)
            )
        }
        DetailRow(icon = Icons.Default.TypeSpecimen, key = "Type:", value = propertyLong.type)
        DetailRow(icon = Icons.Default.Elevator, key = "Floor:", value = propertyLong.floor)
        DetailRow(icon = Icons.Default.Construction, key = "Year of Construction:", value = propertyLong.yearOfConstruction.toString())
        DetailRow(icon = Icons.Default.MeetingRoom, key = "No. of Rooms:", value = propertyLong.rooms.toString())
        DetailRow(icon = Icons.Default.Map, key = "Location:", value = propertyLong.location)
        DetailRow(icon = Icons.Default.MonetizationOn, key = "Price:", value = propertyLong.price)
        

    }


}

@Composable
fun DetailRow(
    icon : ImageVector,
    key : String,
    value : String,
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, GrayIcons, RoundedCornerShape(10))
            .height(30.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

    ){
        Icon(
            icon,
            contentDescription = null,
            modifier  = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)
                .size(20.dp)
        )
        Text(
            text = key
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            modifier = Modifier.padding(end = 8.dp)
        )

    }
}
@Composable
fun BottomButtonsDetailsPropertyComponent(
    propertyLong: PropertyLong
)
{
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)

    ){
        Button(
            onClick = { /* Do nothing as button is inactive */ },
            enabled = true,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors( PrimaryBlue),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .wrapContentHeight()
                .weight(1f)
                .border(1.dp, Color.Gray, RoundedCornerShape(50))
                .height(40.dp)
                .clip(RoundedCornerShape(50))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Icon(
                    Icons.Default.Schedule,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Schedule Viewing",
                    color = White, // Use the appropriate color value
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                )
            }
        }
        Button(
            onClick = { /* Do nothing as button is inactive */ },
            enabled = true,
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors( White),
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .wrapContentHeight()
                .weight(1f)
                .border(1.dp, PrimaryBlue, RoundedCornerShape(50))
                .height(40.dp)
                .clip(RoundedCornerShape(50))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Icon(
                    Icons.Default.Phone,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Call Landlord",
                    color = BlackText, // Use the appropriate color value
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                )
            }
        }
    }
}
@Composable
fun PropertyDetailComponent(
    propertyLong : PropertyLong
)
{

    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        ImageCarousel()
        PropertyLongTextCard(propertyLong = propertyLongDummy)
        Spacer(modifier = Modifier.weight(1f))
        BottomButtonsDetailsPropertyComponent(propertyLong = propertyLongDummy)
    }
}


@Composable
@Preview
fun PropertyDetailComponentPreview(){
    PropertyDetailComponent(propertyLong = propertyLongDummy)
}
