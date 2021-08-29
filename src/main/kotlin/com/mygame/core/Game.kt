package com.mygame.core

import androidx.compose.runtime.State
import androidx.compose.ui.geometry.Offset
import com.mygame.data.GameFrame

/**
 * A generic game interface
 */
interface Game {
    // Generic
    val gameFrame: State<GameFrame>
    fun step()

    // Game specific
    fun onClicked(offset: Offset)
}