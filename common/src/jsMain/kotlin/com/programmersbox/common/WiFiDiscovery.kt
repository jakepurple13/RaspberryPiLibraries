package com.programmersbox.common

import androidx.compose.runtime.Composable

@Composable
public actual fun WiFiDiscoveryScreen(
    onConnect: (url: String) -> Unit,
    openBLEDiscovery: () -> Unit,
    onBackPress: () -> Unit,
    connectivityLocalization: ConnectivityLocalization
) {
    DiscoverWiFiScreen(
        { _, _ -> },
        onConnect,
        openBLEDiscovery,
        onBackPress,
        connectivityLocalization.discoveryText,
        connectivityLocalization.releaseToRefresh,
        connectivityLocalization.refreshing,
        connectivityLocalization.pullToRefresh,
        connectivityLocalization.needToConnectDeviceToWiFiText,
        connectivityLocalization.enterIpAddressText,
        connectivityLocalization.discoverText,
        connectivityLocalization.manualIPText
    )
}