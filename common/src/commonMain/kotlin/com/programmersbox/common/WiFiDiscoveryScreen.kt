package com.programmersbox.common

import androidx.compose.runtime.Composable

@Composable
public expect fun WiFiDiscoveryScreen(
    onConnect: (url: String) -> Unit,
    openBLEDiscovery: () -> Unit,
    onBackPress: () -> Unit,
    connectivityLocalization: ConnectivityLocalization
)