package com.programmersbox.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

public actual fun getPlatformName(): String {
    return "JavaScript"
}

@Composable
public fun UIShow() {
    App()
}

internal actual val hasBLEDiscovery: Boolean = false