package com.example.meditationapp.screens

import android.graphics.Bitmap
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@ExperimentalFoundationApi
@Composable
fun ZoomableImage(
    bitmap: Bitmap,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    imageAlign: Alignment = Alignment.Center,
    shape: Shape = RectangleShape,
    maxScale: Float = 1f,
    minScale: Float = 3f,
    contentScale: ContentScale = ContentScale.Fit,
    isRotation: Boolean = false,
    isZoomable: Boolean = true,
    scrollState: ScrollableState? = null,
    delete: SwipeAction,
    back: SwipeAction
) {
    val coroutineScope = rememberCoroutineScope()

    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    val offsetX = remember { mutableStateOf(1f) }
    val offsetY = remember { mutableStateOf(1f) }

    Box(
        modifier = Modifier
            .clip(shape)
            .background(backgroundColor)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { /* NADA :) */ },
                onDoubleClick = {
                    if (scale.value >= 2f) {
                        scale.value = 1f
                        offsetX.value = 1f
                        offsetY.value = 1f
                    } else scale.value = 3f
                },
            )
            .pointerInput(Unit) {
                if (isZoomable) {
                    forEachGesture {
                        awaitPointerEventScope {
                            awaitFirstDown()
                            do {
                                val event = awaitPointerEvent()
                                scale.value *= event.calculateZoom()
                                if (scale.value > 1) {
                                    scrollState?.run {
                                        coroutineScope.launch {
                                            setScrolling(false)
                                        }
                                    }
                                    val offset = event.calculatePan()
                                    offsetX.value += offset.x
                                    offsetY.value += offset.y
                                    rotationState.value += event.calculateRotation()
                                    scrollState?.run {
                                        coroutineScope.launch {
                                            setScrolling(true)
                                        }
                                    }
                                } else {
                                    scale.value = 1f
                                    offsetX.value = 1f
                                    offsetY.value = 1f
                                }
                            } while (event.changes.any { it.pressed })
                        }
                    }
                }
            }

    ) {
        if (scale.value == 1f){
            SwipeableActionsBox(
                startActions = listOf(delete),
                endActions = listOf(back)
            ){
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = contentScale,
                    modifier = modifier
                        .align(imageAlign)
                        .graphicsLayer {
                            if (isZoomable) {
                                scaleX = maxOf(maxScale, minOf(minScale, scale.value))
                                scaleY = maxOf(maxScale, minOf(minScale, scale.value))
                                if (isRotation) {
                                    rotationZ = rotationState.value
                                }
                                translationX = offsetX.value
                                translationY = offsetY.value
                            }
                        }
                )
            }
        } else{
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                contentScale = contentScale,
                modifier = modifier
                    .align(imageAlign)
                    .graphicsLayer {
                        if (isZoomable) {
                            scaleX = maxOf(maxScale, minOf(minScale, scale.value))
                            scaleY = maxOf(maxScale, minOf(minScale, scale.value))
                            if (isRotation) {
                                rotationZ = rotationState.value
                            }
                            translationX = offsetX.value
                            translationY = offsetY.value
                        }
                    }
            )
        }

    }
}

suspend fun ScrollableState.setScrolling(value: Boolean) {
    scroll(scrollPriority = MutatePriority.PreventUserInput) {
        when (value) {
            true -> Unit
            else -> awaitCancellation()
        }
    }
}