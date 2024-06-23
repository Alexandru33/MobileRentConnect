package ro.araducanu.rentconnect.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import ro.araducanu.rentconnect.components.ViewingCard
import ro.araducanu.rentconnect.data.models.Viewing
import ro.araducanu.rentconnect.data.viewmodels.ViewingViewModel
@Composable
fun ViewingsScreen(viewingsViewModel: ViewingViewModel = viewModel() ) {
    val landlordViewings by viewingsViewModel.landlordViewings.observeAsState(initial = emptyList())
    val tenantViewings by viewingsViewModel.tenantViewings.observeAsState(initial = emptyList())

    viewingsViewModel.getViewingsOfEmailLandlord(FirebaseAuth.getInstance().currentUser?.email!!)
    viewingsViewModel.getViewingsOfEmailTenant(FirebaseAuth.getInstance().currentUser?.email!!)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ViewingList(
            title = "Your next viewings as the Landlord",
            viewings = landlordViewings,
            onViewingClick = {}
        )
        Spacer(modifier = Modifier.height(32.dp)) // Add space between sections
        ViewingList(
            title = "Your next viewings as the Tenant",
            viewings = tenantViewings,
            onViewingClick = {}
        )
    }
}



@Composable
fun ViewingList(title: String, viewings: List<Viewing>, onViewingClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            viewings.forEach { viewing ->
                ViewingCard(viewing = viewing, onCLick = {})
                Spacer(modifier = Modifier.height(8.dp)) // Add space between cards
            }
        }
    }
}