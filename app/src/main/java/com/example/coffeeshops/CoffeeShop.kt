package com.example.coffeeshops

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshops.ui.theme.FontCoffee
import com.example.coffeeshops.ui.theme.Pink80
import com.example.coffeeshops.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CoffeeShop(navController: NavController, viewModel: CoffeeShopsViewModel) {
    val selectedEstablishment = viewModel.selectedEstablishment
    val listState = rememberLazyStaggeredGridState()
    val buttonVisible = remember { mutableStateOf(true) }
    val comments = listOf<String>(
        "Some loose, I still recommend it.",
        "Centeral and cozy. We'll come back safely.",
        "The setting very good, but on the top floor a little bit...",
        "The food was delicious and quite well priced, lots of variety of dishes.",
        "The very friendly staff, they allowed us to see the whole establishment.",
        "Very good.","Excellent. Highlight the extensive coffee chart." ,"Good atmos,phere and good service. I recommend it.",
        "On holidays too much waiting time. The waiters are not enough. I don't recommend it. I won't come back." ,
        "We will repeat. Great selection of cakes and coffees.", "Everything I've tried in the cafeteria is rich, sweet or salty." ,
        "The very nice dishes all of design that in the surroundings of the bar is ideal.",
        "Negative points: the service is very slow and prices are a little high."
    )
    Column() {
        Spacer(modifier = Modifier.height(60.dp))
        Row() {
            Spacer(modifier = Modifier.weight(0.2f))
            Text(
                text = selectedEstablishment.toString(),
                fontFamily = FontCoffee,
                fontSize = 38.sp,
                color = Color.Black,
                modifier = Modifier.padding(12.dp)
            )
            Spacer(modifier = Modifier.weight(0.2f))
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(10.dp),
            state = listState
        ) {
            items(comments.size) { index ->
                Card(
                    modifier = Modifier.padding(10.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(Purple80)
                ) {
                    Text(
                        text = comments[index],
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 4.dp, top = 8.dp, bottom = 8.dp))
                }


            }

            val scrollOffset = listState.firstVisibleItemScrollOffset
            if (scrollOffset > 0 && buttonVisible.value) {
                buttonVisible.value = false
            } else if (scrollOffset == 0 && !buttonVisible.value) {
                buttonVisible.value = true
            }

        }
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        if (!buttonVisible.value) {
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Pink80),
            ) {
                Text(text = "Add new comment")
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate("CoffeeShops")
            },
            shape = RoundedCornerShape(30.dp),
            containerColor = Pink80,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}

@Composable
fun MyDropDownMenuForItem() {
    var selectedText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val items = listOf<String>("Compartir", "Guardar")
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
                            Icon(painter = painterResource(R.drawable.baseline_share_24),
                                contentDescription = null,
                                tint = Color.Black)
                        } else {
                            Icon(painter = painterResource(id = R.drawable.baseline_save_24),
                                contentDescription = null,
                                tint = Color.Black)
                        }
                    },
                    text = { Text(text = it, color = Color.Black , fontSize = 16.sp)},
                    onClick = {
                        selectedText = it
                    }
                )
            }
        }

    }
}