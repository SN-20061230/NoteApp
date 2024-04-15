package com.example.noteapp2.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController,) {
    LaunchedEffect(key1 = true) {
        delay(1500)
        navController.navigate("home_screen")

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    )
    {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/31827829-c1d3-4d00-9361-47685fa5e7ec/1ydk2LdvsN.lottie"))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
    }
}