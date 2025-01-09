package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource

@Composable
fun SpinWheelComponent(spinWheelState: SpinWheelState) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.769f)
    ) {
        spinWheelState.backgroundImage?.let {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(resource = spinWheelState.backgroundImage),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(0.769f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(27f))
            Image(
                modifier = Modifier
                    .weight(12f)
                    .aspectRatio(1f),
                painter = painterResource(resource = spinWheelState.indicatorImage),
                contentDescription = null
            )
            BoxWithConstraints(
                modifier = Modifier
                    .weight(82f)
                    .aspectRatio(1f)
            ) {
                val imageSize = this.maxHeight.times(0.14f)
                SpinWheel(modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationZ = spinWheelState.rotation.value
                    }, items = spinWheelState.items)
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(imageSize),
                    painter = painterResource(resource = spinWheelState.centerImage),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.weight(9f))
        }
    }
}