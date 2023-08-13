package com.example.catre.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.navigation.NavController
import com.example.catre.Navigation.AppScreens
import com.example.catre.R
import com.example.catre.ViewModel.ToDoViewModel
import java.time.LocalTime

@Composable
fun HomeScreen(navController: NavController, viewModel: ToDoViewModel) {
    val checkedStates = viewModel.getCheckedStates()
    val currentTime = LocalTime.now()

    val dueTimeBreakfast = currentTime.plusHours(7)
    val dueTimeLunch = currentTime.plusHours(12)
    val dueTimeDinner = currentTime.plusHours(19)
    val dueTimeLitterBox = currentTime.plusHours(5)
    val dueTimeBathing = currentTime.plusHours(10)
    val dueTimePlaying = currentTime.plusHours(15)

    val isBreakfastOverdue = !checkedStates[0] && currentTime.isAfter(dueTimeBreakfast)
    val isLunchOverdue = !checkedStates[1] && currentTime.isAfter(dueTimeLunch)
    val isDinnerOverdue = !checkedStates[2] && currentTime.isAfter(dueTimeDinner)
    val isLitterBoxOverdue = !checkedStates[3] && currentTime.isAfter(dueTimeLitterBox)
    val isBathingverdue = !checkedStates[4] && currentTime.isAfter(dueTimeBathing)
    val isPlayingOverdue = !checkedStates[5] && currentTime.isAfter(dueTimePlaying)

    val cat = when {
        checkedStates[0] -> R.drawable.happypic
        isLitterBoxOverdue -> R.drawable.disgustedpic
        isDinnerOverdue -> R.drawable.hungrypic
        isLunchOverdue -> R.drawable.hungrypic
        isBreakfastOverdue -> R.drawable.hungrypic
        isBathingverdue -> R.drawable.dirty
        isPlayingOverdue -> R.drawable.bored
        else -> R.drawable.happypic // Default image
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
            painter = painterResource(id = R.drawable.screen1),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(70.dp)
                    .clickable {
                        navController.navigate(AppScreens.TaskScreen.route)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.rightarrow),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(70.dp)
                    .clickable {
                        navController.navigate(AppScreens.LevelScreen.route)
                    }
            )
            Image(
                painter = painterResource(id = cat),
                contentDescription = "CatState",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }
    }
}