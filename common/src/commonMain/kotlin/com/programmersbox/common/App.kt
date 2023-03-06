package com.programmersbox.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

public val LocalConnectivityLocale: ProvidableCompositionLocal<ConnectivityLocalization> =
    staticCompositionLocalOf { error("Nothing Here!") }

@Composable
internal fun App() {
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(getPlatformName())
        }
    }
}