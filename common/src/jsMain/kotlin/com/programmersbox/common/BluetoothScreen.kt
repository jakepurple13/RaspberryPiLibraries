package com.programmersbox.common

import androidx.compose.runtime.Composable

@Composable
public actual fun BluetoothDiscovery(
    onBackPress: () -> Unit,
    onConnect: () -> Unit,
    connectivityLocalization: ConnectivityLocalization
) {
    error("Web is not supported")
}