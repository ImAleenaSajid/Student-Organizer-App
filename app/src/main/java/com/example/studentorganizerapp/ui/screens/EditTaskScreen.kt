package com.example.studentorganizerapp.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studentorganizerapp.data.model.Task
import com.example.studentorganizerapp.viewmodel.TaskViewModel
import com.example.studentorganizerapp.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTaskScreen(
    navController: NavController,
    taskViewModel: TaskViewModel,
    task: Task
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    var showPopup by remember { mutableStateOf(false) }

    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffsetX by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val buttonAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(showPopup) {
        if (showPopup) {
            snackbarHostState.showSnackbar("Edited Successfully")
            delay(1000)
            showPopup = false
        }
    }

    var taskName by remember { mutableStateOf(task.title) }
    var taskDate by remember { mutableStateOf(task.date) }
    var selectedCategory by remember { mutableStateOf(task.category) }
    val categories = listOf("Lecture", "Exam", "Assignment")
    var expanded by remember { mutableStateOf(false) }

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
                                text = "EDIT TASK",
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
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .border(4.dp, Color(0xFF87CEEB)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.background3),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.4f
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()
            ) {
                Text(
                    "Edit Task",
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                        shadow = Shadow(
                            color = Color.Gray,
                            offset = Offset(2f, 2f),
                            blurRadius = 4f
                        )
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = {
                        Text("Task Title", fontFamily = FontFamily.Cursive, fontSize = 18.sp)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, MaterialTheme.shapes.small)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = taskDate,
                    onValueChange = { taskDate = it },
                    label = {
                        Text("Due Date", fontFamily = FontFamily.Cursive, fontSize = 18.sp)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, MaterialTheme.shapes.small)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedButton(
                        onClick = { expanded = true },
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxWidth()
                            .border(2.dp, Color.Black)
                    ) {
                        Text(
                            selectedCategory,
                            fontFamily = FontFamily.Cursive,
                            fontSize = 18.sp
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        category,
                                        fontFamily = FontFamily.Cursive
                                    )
                                },
                                onClick = {
                                    selectedCategory = category
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (taskName.isNotEmpty() && taskDate.isNotEmpty()) {
                            val updatedTask = task.copy(
                                title = taskName,
                                date = taskDate,
                                category = selectedCategory
                            )
                            taskViewModel.updateTask(updatedTask)
                            showPopup = true
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(60.dp)
                        .graphicsLayer(alpha = buttonAlpha),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Text(
                        "Update Task",
                        color = Color.White,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .size(70.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(48.dp)
                        .graphicsLayer(alpha = buttonAlpha),
                    tint = Color.White
                )
            }
        }
    }
}
