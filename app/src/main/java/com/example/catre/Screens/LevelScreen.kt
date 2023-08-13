package com.example.catre.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.catre.Navigation.AppScreens
import com.example.catre.R
import com.example.catre.ViewModel.ToDoViewModel

@Composable
fun LevelScreen(navController: NavController, viewModel: ToDoViewModel) {
    val checkedStates = viewModel.getCheckedStates()

    val totalChecks = checkedStates.count { it }
    val levelThresholds = listOf(5, 20, 50)
    val levelNames = listOf("Level 0", "Level 1", "Level 2", "Level 3")

    var currentLevel = 0
    while (currentLevel < levelThresholds.size && totalChecks >= levelThresholds[currentLevel]) {
        currentLevel++
    }

    val checksToNextLevel = if (currentLevel < levelThresholds.size) {
        levelThresholds[currentLevel] - totalChecks
    } else {
        0 // Max level reached
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
            painter = painterResource(id = R.drawable.screen3),
            contentDescription = "bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // White circle with current level
            Box(
                modifier = Modifier
                    .size(270.dp)
                    .background(Color.White, CircleShape)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = levelNames[currentLevel],
                        style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Bold, color = colorResource(id = R.color.barbar))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Current Level",
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Progress bar for current level
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                val totalChecksForCurrentLevel = if (currentLevel > 0) {
                    totalChecks - levelThresholds[currentLevel - 1]
                } else {
                    totalChecks
                }

                LinearProgressIndicator(
                    progress = totalChecksForCurrentLevel.toFloat() / levelThresholds[currentLevel].toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = 32.dp, vertical = 5.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Checks: $totalChecksForCurrentLevel of ${levelThresholds[currentLevel]}",
                        style = TextStyle(fontSize = 14.sp),
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(70.dp)
                    .clickable {
                        navController.navigate(AppScreens.HomeScreen.route)
                    }
            )
        }
    }
}