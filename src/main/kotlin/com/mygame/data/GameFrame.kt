package com.mygame.data

import androidx.compose.ui.graphics.Color

data class Circle(
    val color: Color,
    val radius: Float,
    val x: Float,
    val y: Float
)

data class GameFrame(
    val score: Int,
    val circles: List<Circle>
)