package com.example.studentorganizerapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.studentorganizerapp.R
import com.example.studentorganizerapp.data.model.Task
import com.example.studentorganizerapp.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    navController: NavHostController,
    taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.allTasks.observeAsState(emptyList())
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffsetX by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Scaffold(
    topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 3.dp, color = Color.White)
        ) {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "STUDENT ORGANIZER",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp,
                                textAlign = TextAlign.Center,
                                shadow = Shadow(
                                    color = Color.Black,
                                    offset = Offset(4f, 4f),
                                    blurRadius = 6f
                                ),
                                fontFamily = FontFamily.Cursive
                            ),
                            color = Color.White,
                            modifier = Modifier.graphicsLayer {
                                translationX = animatedOffsetX
                            }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF87CEEB).copy(alpha = 0.3f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )
        }
    }
    ,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addTask") },
                modifier = Modifier
                    .padding(16.dp)
                    .size(72.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                val infiniteTransition = rememberInfiniteTransition()
                val alpha by infiniteTransition.animateFloat(
                    initialValue = 0.3f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1000),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Text(
                    "+",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.alpha(alpha)
                )
            }
        }
    ) { paddingValues ->
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.bodyLarge.copy(
                fontFamily = FontFamily.Cursive
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .border(
                        width = 4.dp,
                        color = Color(0xFF87CEEB)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background3),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.4f),
                    contentScale = ContentScale.Crop
                )

                if (tasks.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "NO PENDING TASKS!",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily.Cursive,
                                fontWeight = FontWeight.Bold,
                                fontSize = 35.sp, // Increased size
                                textAlign = TextAlign.Center
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        val infiniteTransition = rememberInfiniteTransition()
                        val alpha by infiniteTransition.animateFloat(
                            initialValue = 1f,
                            targetValue = 0.4f,
                            animationSpec = infiniteRepeatable(
                                animation = tween(durationMillis = 600),
                                repeatMode = RepeatMode.Reverse
                            )
                        )

                        Button(
                            onClick = { navController.navigate("addTask") },
                            modifier = Modifier
                                .size(200.dp, 70.dp) // Increased button size
                                .graphicsLayer(alpha = alpha) // Flickering effect
                        ) {
                            Text(
                                "Add Task",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontFamily = FontFamily.Cursive,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp // Larger font size for button text
                                )
                            )
                        }
                    }
                }
                else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(tasks) { task ->
                            TaskItem(task = task, taskViewModel = taskViewModel, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, taskViewModel: TaskViewModel, navController: NavHostController) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Title: ${task.title}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Due: ${task.date}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "Category: ${task.category}",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("editTask/${task.id}")
                    },
                    modifier = Modifier.graphicsLayer(alpha = alpha), // Flicker only on Edit button
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4169E1)
                    )
                ) {
                    Text(
                        "Edit Task",
                        style = TextStyle(
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                Button(
                    onClick = { taskViewModel.deleteTask(task) },
                    modifier = Modifier.graphicsLayer(alpha = alpha), // Flicker only on Delete button
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(
                        "Delete Task",
                        style = TextStyle(
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

