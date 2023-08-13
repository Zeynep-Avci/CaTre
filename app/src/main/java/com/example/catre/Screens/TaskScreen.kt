package com.example.catre.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catre.Navigation.AppScreens
import com.example.catre.R
import com.example.catre.ViewModel.ToDoViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

@Composable
fun TaskScreen(navController: NavController, viewModel: ToDoViewModel) {
    val todoItems = viewModel.getTodoItems()
    val checkedStates = viewModel.getCheckedStates()
    val coroutineScope = rememberCoroutineScope()

    // Automatic reset after midnight
    LaunchedEffect(true) {
        val currentTime = LocalTime.now()
        val resetTime = LocalTime.of(0, 0)

        val timeUntilReset = if (currentTime.isBefore(resetTime)) {
            resetTime.toSecondOfDay() - currentTime.toSecondOfDay()
        } else {
            resetTime.toSecondOfDay() + (24 * 60 * 60) - currentTime.toSecondOfDay()
        }

        coroutineScope.launch {
            while (true) {
                delay(timeUntilReset * 1000L) // Convert seconds to milliseconds
                val updatedCheckedStates = List(todoItems.size) { false }
                viewModel.updateCheckedStates(updatedCheckedStates)
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f)) // Add spacer to center the logo
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.textlogo),
                            contentDescription = "MyLogo",
                            modifier = Modifier
                                .size(100.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.width(35.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.barbar),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Points: ${viewModel.points.value}",
                            modifier = Modifier.align(Alignment.CenterEnd),
                            color = Color.White,
                            style = TextStyle(fontSize = 16.sp)
                        )
                    }
                }
            },
            backgroundColor = Color.White
        )
    }) {
        Image(
            painter = painterResource(id = R.drawable.screen2),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ){
                itemsIndexed(items = todoItems) {index, task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .background(Color.Gray, RoundedCornerShape(8.dp)),
                        elevation = 4.dp
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Checkbox(
                                checked = checkedStates[index],
                                onCheckedChange = { newValue ->
                                    val newCheckedStates = checkedStates.toMutableList()
                                    newCheckedStates[index] = newValue
                                    viewModel.updateCheckedStates(newCheckedStates)
                                    if (newValue) {
                                        viewModel.incrementPoints()
                                    } else {
                                        viewModel.decrementPoints()
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.Green,
                                    uncheckedColor = Color.Red
                                )
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = task,
                                fontSize = 18.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }

                }
            }
            Image(
                painter = painterResource(id = R.drawable.rightarrow),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(70.dp)
                    .clickable{
                        navController.navigate(AppScreens.HomeScreen.route)
                    }
            )


        }


    }

}

