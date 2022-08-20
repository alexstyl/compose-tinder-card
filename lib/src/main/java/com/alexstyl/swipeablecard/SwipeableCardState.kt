package com.alexstyl.swipeablecard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


enum class Direction {
    Left, Right
}

@Composable
fun rememberSwipeableCardState(): SwipeableCardState {
    val screenWidth = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }
    val screenHeight = with(LocalDensity.current) {
        LocalConfiguration.current.screenHeightDp.dp.toPx()
    }
    return remember {
        SwipeableCardState(screenWidth, screenHeight)
    }
}


class SwipeableCardState(
    internal val maxWidth: Float,
    internal val maxHeight: Float,
) {
    val offset = Animatable(Offset(0f, 0f), Offset.VectorConverter)

    /**
     * The [Direction] the card was swiped at.
     *
     * Null value means the card has not been swiped fully yet.
     */
    var swipedDirection: Direction? by mutableStateOf(null)
        private set

    internal suspend fun reset() {
        offset.animateTo(Offset(0f, 0f), tween(400))
    }

    suspend fun swipe(direction: Direction, animationSpec: AnimationSpec<Offset> = tween(400)) {
        val endX = maxWidth * 1.5f
        when (direction) {
            Direction.Left -> offset.animateTo(offset.value.withX(-endX), animationSpec)
            Direction.Right -> offset.animateTo(offset.value.withX(endX), animationSpec)
        }
        this.swipedDirection = direction
    }

    internal suspend fun drag(x: Float, y: Float) {
        offset.animateTo(Offset(x, y))
    }
}

private fun Offset.withX(x: Float): Offset {
    return Offset(x, this.y)
}
