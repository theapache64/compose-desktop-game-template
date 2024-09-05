package com.mygame

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowSize
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.mygame.core.Game
import com.mygame.core.MyGame
import kotlinx.coroutines.delay

/**
 * Game's entry point
 */
fun main() = application {

    println("Game started")

    val windowState = rememberWindowState(
        size = DpSize(MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT)
    )
    Window(onCloseRequest = ::exitApplication, state = windowState) {
        Game()
    }
}


@Composable
fun Game() {
    val game: Game = remember { MyGame() }
    val gameFrame = game.gameFrame.value

    LaunchedEffect(Unit) {
        // Game loop
        while (true) {
            game.step()
            delay(16)
        }
    }

    Box(
        modifier = Modifier.padding(10.dp)
    ) {
        Text("Score: ${gameFrame.score}", fontSize = 20.sp)

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        // Passing event to game
                        game.onClicked(offset)
                    }
                }
        ) {
            // Drawing circles
            for (circle in gameFrame.circles) {
                drawCircle(
                    circle.color,
                    circle.radius,
                    Offset(circle.x, circle.y)
                )
            }
        }
    }
}

