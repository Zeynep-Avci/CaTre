package com.example.catre.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.navigation.NavController
import com.example.catre.Navigation.AppScreens
import com.example.catre.R
import com.example.catre.ViewModel.ToDoViewModel
import java.time.LocalTime


data class Badge(val task: String, val unlockRequirement: Int, val lockedImage: Int, val unlockedImage: Int)

@Composable
fun BadgeScreen(navController: NavController, viewModel: ToDoViewModel) {
    val totalPoints = viewModel.points.value

    val badges = listOf(
        Badge("Check your first task", 1, R.drawable.badge1_locked, R.drawable.badge1),
        Badge("Check one day", 6, R.drawable.badge2_locked, R.drawable.badge2),
        Badge("Complete 50 tasks", 50, R.drawable.badge3_locked, R.drawable.badge3),
        Badge("Reach 100 points", 100, R.drawable.badge4_locked, R.drawable.badge4)
    )

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
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.screen4),
                contentDescription = "bg",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(130.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    badges.subList(0, 2).forEach { badge ->
                        BadgeCard(badge, totalPoints, cardSize = 170.dp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    badges.subList(2, 4).forEach { badge ->
                        BadgeCard(badge, totalPoints, cardSize = 170.dp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.leftarrow),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(70.dp)
                    .clickable {
                        navController.navigate(AppScreens.LevelScreen.route)
                    }
                    .align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
fun BadgeCard(badge: Badge, totalPoints: Int, cardSize: Dp) {
    val isUnlocked = totalPoints >= badge.unlockRequirement

    Card(
        modifier = Modifier
            .size(cardSize)
            .background(
                color = if (isUnlocked) Color.White else Color.Gray,
                shape = RoundedCornerShape(16.dp)
            ),
        elevation = 4.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = if (isUnlocked) badge.unlockedImage else badge.lockedImage),
                contentDescription = "Badge",
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.CenterHorizontally)
            )
            //Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = badge.task,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                //fontWeight = FontWeight.Bold,
                //color = if (!isUnlocked) Color.Black else Color.White
            )
        }
    }
}
