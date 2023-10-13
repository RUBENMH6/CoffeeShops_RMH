package com.example.coffeeshops

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.coffeeshops.ui.theme.FontCoffee
import com.example.coffeeshops.ui.theme.Pink40
import com.example.coffeeshops.ui.theme.Pink80
import com.example.coffeeshops.ui.theme.Purple40
import com.example.coffeeshops.ui.theme.Purple80


@Composable
fun CoffeeShops(navController: NavController, viewModel: CoffeeShopsViewModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(getCardList()) {
                Spacer(modifier = Modifier.weight(0.05f))
                MyCard(it, navController, viewModel)
                Spacer(modifier = Modifier.weight(0.05f))
            }
        }

    }

}

@Composable
fun MyCard(cardInfo: CardInfo, navController: NavController, viewModel: CoffeeShopsViewModel) {
    var rating by remember { mutableStateOf(0f) }
    Spacer(modifier = Modifier.height(20.dp))
    Card(
        colors = CardDefaults.cardColors(Purple80),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.clickable {
            viewModel.selectEstablishment(cardInfo)
            navController.navigate(Routes.CoffeeShop.route)
        }
    ) {
        Image(
            painter = painterResource(cardInfo.image),
            contentDescription = cardInfo.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = cardInfo.name,
            fontFamily = FontCoffee,
            fontSize = 38.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 12.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        MyRatingBar(rating, { rating = it })
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = cardInfo.adress,
            color = Color.Black,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Divider()
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "RESERVE",
                color = Pink80,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(0.9f))
        }

    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun MyRatingBar(ratingValue: Float, onRatingChanged: (Float) -> Unit) {
    var estadoRating = 0f
    var maxRating = 5
    val iconStar = R.drawable.baseline_star_24
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(0.2f))
            for (rating in 1..maxRating) {
                Icon(
                    painter = painterResource(iconStar),
                    contentDescription = "star",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            estadoRating = rating.toFloat()
                            onRatingChanged(rating.toFloat())
                        },
                    tint =
                    if (rating <= estadoRating || rating <= ratingValue) {
                        Color.Black
                    } else {
                        Color.White
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Spacer(modifier = Modifier.weight(0.2f))
        }

        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Composable
fun MyDropDownMenu() {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val items = listOf<String>("Compartir", "Album")
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        Spacer(modifier = Modifier.weight(0.9f))
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
    Row(
    ) {
        Spacer(modifier = Modifier.height(65.dp))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Purple80)
        ) {
            items.forEach {
                DropdownMenuItem(
                    leadingIcon = {
                        if (it == "Compartir") {
                            Icon(
                                painter = painterResource(R.drawable.baseline_share_24),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_lock_24),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    },
                    text = { Text(text = it, color = Color.Black, fontSize = 16.sp) },
                    onClick = {
                        selectedText = it
                    }
                )
            }
        }

    }
}

