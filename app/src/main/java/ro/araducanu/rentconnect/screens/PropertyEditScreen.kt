package ro.araducanu.rentconnect.screens


import android.app.TimePickerDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import ro.araducanu.rentconnect.components.ImageCarousel
import ro.araducanu.rentconnect.components.PropertyLongTextCard
import ro.araducanu.rentconnect.components.ViewingCard
import ro.araducanu.rentconnect.data.models.PropertyLong
import ro.araducanu.rentconnect.data.models.Viewing
import ro.araducanu.rentconnect.data.viewmodels.PropertyImageViewModel
import ro.araducanu.rentconnect.data.viewmodels.PropertyViewModel
import ro.araducanu.rentconnect.data.viewmodels.ViewingViewModel
import ro.araducanu.rentconnect.navigation.RentConnectAppRouter
import ro.araducanu.rentconnect.navigation.Screen
import ro.araducanu.rentconnect.ui.theme.BlackText
import ro.araducanu.rentconnect.ui.theme.PrimaryBlue
import ro.araducanu.rentconnect.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun PropertyEditScreen(
    propertyViewModel : PropertyViewModel = viewModel(),
    viewingViewModel : ViewingViewModel = viewModel()
) {


    val selectedProperty by propertyViewModel.selectedProperty.observeAsState()


    val viewingsOfThisProperty by viewingViewModel.viewingsList.observeAsState(initial = emptyList())


    viewingViewModel.getViewingsOfPropertyIdWithStatus(selectedProperty?.id!!, "free")


    var showDialog by remember { mutableStateOf(false) }


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
                .verticalScroll(rememberScrollState())
        ) {
            ImageCarousel(PropertyImageViewModel(), selectedProperty?.id!!)


            PropertyLongTextCard(propertyLong = selectedProperty!!)
            Spacer(modifier = Modifier.weight(1f))
            //BottomButtonsDetailsPropertyComponent(propertyLong = propertyLong)
            //PropertyLongTextCard(propertyLong = propertyLongDummy)
            //Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                viewingsOfThisProperty.forEach { viewing ->
                    ViewingCard(viewing, {} )
                    Spacer(modifier = Modifier.height(8.dp)) // Add space between cards
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp)

            ) {
                Button(
                    onClick =
                    {
                    showDialog = true
                    },
                    enabled = true,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(PrimaryBlue),
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
                            Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Add Viewing",
                            color = White, // Use the appropriate color value
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                        )
                    }

                }
                Button(
                    onClick = {
                              RentConnectAppRouter.navigateTo(Screen.PropertyModifyScreen)

                    },
                    enabled = true,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(White),
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
                            Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.Black
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Edit",
                            color = BlackText, // Use the appropriate color value
                            fontSize = 12.sp,
                            modifier = Modifier.padding(start = 5.dp) // Apply padding to the text instead
                        )
                    }
                }
            }
        }

        if (showDialog)
        {
            AddViewingPopup( onDismiss = { showDialog = false }, selectedProperty!!)
        }
    }
}


@Composable
fun AddViewingPopup(
    onDismiss: () -> Unit,
    propertyLong : PropertyLong,
    viewingViewModel : ViewingViewModel = viewModel()
) {


    val context = LocalContext.current
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Button(
                    onClick = {
                        showDatePicker(context = context)
                            { date ->
                            selectedDate = date }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Select Date")
                }
                Text(text = selectedDate)

                Button(
                    onClick = {
                        showTimePicker(context = context){ time ->
                            selectedTime = time
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Select Time")
                }
                Text(text = selectedTime)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                    ) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            var viewingToAdd = Viewing()

                            viewingToAdd.date = Timestamp(
                                SimpleDateFormat(
                                    "dd/MM/yyyy",
                                    Locale.getDefault()
                                ).parse(selectedDate)!!
                            )
                            viewingToAdd.time = selectedTime
                            viewingToAdd.status = "free"
                            viewingToAdd.idProperty = propertyLong.id!!
                            viewingToAdd.emailTenant = ""
                            viewingToAdd.emailLandlord = FirebaseAuth.getInstance().currentUser?.email!!

                            viewingViewModel.addViewing(viewingToAdd)
                            viewingViewModel.getViewingsOfPropertyIdWithStatus(propertyLong.id!!, "free")
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}

fun showDatePicker(context : Context, onDateSelected: (String) -> Unit ) {
    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        // Your context here, for example, LocalContext.current
        context,
        { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            onDateSelected(date)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
}

fun showTimePicker(context : Context , onTimeSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    TimePickerDialog(
        // Your context here, for example, LocalContext.current
        context,
        { _, hourOfDay, minute ->
            val time = "$hourOfDay:$minute"
            onTimeSelected(time)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    ).show()
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PropertyEditScreenPreview() {
    PropertyEditScreen()
}