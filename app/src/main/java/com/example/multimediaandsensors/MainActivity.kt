package com.example.multimediaandsensors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.multimediaandsensors.ui.camera.CameraScreen
import com.example.multimediaandsensors.ui.home.HomeScreen
import com.example.multimediaandsensors.ui.music.MusicScreen
import com.example.multimediaandsensors.ui.sensor.StepCounterScreen
import com.example.multimediaandsensors.ui.theme.MultimediaAndSensorsTheme
import com.example.multimediaandsensors.ui.video.VideoScreen

const val HOME = "home"
const val CAMERA = "camera"
const val MUSIC = "music"
const val VIDEO = "video"
const val SENSOR = "sensor"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultimediaAndSensorsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HOME
    ) {
        composable(
            route = HOME,
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() }
        ) {
            HomeScreen(
                onCameraClick = { navController.navigate(CAMERA) },
                onMusicClick = { navController.navigate(MUSIC) },
                onVideoClick = { navController.navigate(VIDEO) },
                onSensorClick = { navController.navigate(SENSOR) }
            )
        }
        composable(
            route = CAMERA,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
            }
        ) {
            CameraScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = MUSIC,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() },
            popEnterTransition = { fadeIn() },
            popExitTransition = { fadeOut() }
        ) {
            MusicScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = VIDEO,
            enterTransition = { scaleIn() + fadeIn() },
            exitTransition = { scaleOut() + fadeOut() },
            popEnterTransition = { scaleIn() + fadeIn() },
            popExitTransition = { scaleOut() + fadeOut() }
        ) {
            VideoScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = SENSOR,
            enterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Up)
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Down)
            },
            popExitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down)
            }
        ) {
            StepCounterScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
