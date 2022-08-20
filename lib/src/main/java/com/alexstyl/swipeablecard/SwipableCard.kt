package com.alexstyl.swipeablecard

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Enables Tinder like swiping gestures.
 *
 * @param state The current state of the swipeable card. Use [_root_ide_package_.com.alexstyl.swipeablecard.rememberSwipeableCardState()] to create.
 * @param onSwiped will be called once a swipe gesture is completed. The given [Direction] will indicate which side the gesture was performed on.
 * @param onSwipeCancel will be called when the gesture is stopped before reaching the minimum threshold to be treated as a full swipe
 */
@ExperimentalSwipeableCardApi
fun Modifier.swipableCard(
    state: SwipeableCardState,
    onSwiped: (Direction) -> Unit,
    onSwipeCancel: () -> Unit = {},
) = pointerInput(Unit) {
    coroutineScope {
        detectDragGestures(
            onDragCancel = {
                launch {
                    state.reset()
                    onSwipeCancel()
                }
            },
            onDrag = { change, dragAmount ->
                launch {
                    val original = state.offset.targetValue
                    val summed = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-state.maxWidth, state.maxWidth),
                        y = summed.y.coerceIn(-state.maxHeight, state.maxHeight)
                    )
                    if (change.positionChange() != Offset.Zero) change.consume()
                    state.drag(newValue.x, newValue.y)
                }
            },
            onDragEnd = {
                when {
                    abs(state.offset.targetValue.x) < state.maxWidth / 4 -> {
                        launch {
                            state.reset()
                            onSwipeCancel()
                        }
                    }
                    state.offset.targetValue.x > 0 -> {
                        launch {
                            state.swipe(Direction.Right)
                            onSwiped(Direction.Right)
                        }
                    }
                    state.offset.targetValue.x < 0 -> {
                        launch {
                            state.swipe(Direction.Left)
                            onSwiped(Direction.Left)
                        }
                    }
                }
            }
        )
    }
}.graphicsLayer(
    translationX = state.offset.value.x,
    translationY = state.offset.value.y,
    rotationZ = (state.offset.value.x / 60).coerceIn(-40f, 40f),
)

