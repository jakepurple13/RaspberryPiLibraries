package com.programmersbox.common

import androidx.compose.runtime.Composable

@Composable
public expect fun BluetoothDiscovery(
    onBackPress: () -> Unit,
    onConnect: () -> Unit,
    connectivityLocalization: ConnectivityLocalization
)