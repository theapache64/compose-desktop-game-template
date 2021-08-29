package com.mygame.core

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mygame.data.Circle
import com.mygame.data.GameFrame

class MyGame : Game {

    companion object {
        // Static game configs goes here
        val SCREEN_WIDTH = 1000.dp
        val SCREEN_HEIGHT = 600.dp
    }

    private val _gameFrame by lazy {
        mutableStateOf(
            // First frame
            GameFrame(score = 0, circles = mutableListOf())
        )
    }
    override val gameFrame: State<GameFrame> = _gameFrame

    override fun step() {
        update {
            copy(
                score = circles.size,
                circles = newCircles(circles)
            )
        }
    }

    private fun newCircles(circles: List<Circle>): List<Circle> {
        // Incrementing circle's y axis
        return circles.map { circle ->
            val newY = if (circle.y > SCREEN_HEIGHT.value) {
                0f // reset
            } else {
                circle.y + 10 // move down
            }

            circle.copy(y = newY)
        }
    }

    override fun onClicked(offset: Offset) {
        // Adding a new circle on each click
        update {
            copy(
                circles = circles.toMutableList().apply {
                    add(Circle(Color.Red, 10f, offset.x, offset.y))
                }
            )
        }
    }

    private inline fun update(func: GameFrame.() -> GameFrame) {
        _gameFrame.value = _gameFrame.value.func()
    }

}