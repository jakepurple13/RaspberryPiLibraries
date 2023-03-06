package com.programmersbox.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember

@Composable
public actual fun BluetoothDiscovery(
    onBackPress: () -> Unit,
    onConnect: () -> Unit,
    connectivityLocalization: ConnectivityLocalization
) {
    val vm = remember { BluetoothViewModel(onConnect) }
    DisposableEffect(Unit) { onDispose { vm.disconnect() } }
    BluetoothDiscoveryScreen(
        state = vm.state,
        isConnecting = vm.connecting,
        device = vm.advertisement,
        deviceList = vm.advertisementList,
        onDeviceClick = { it?.let(vm::click) },
        deviceIdentifier = { it?.identifier?.UUIDString.orEmpty() },
        deviceName = { it?.name ?: it?.peripheralName ?: "Device" },
        isDeviceSelected = { found, selected -> found?.identifier == selected?.identifier },
        networkItem = vm.networkItem,
        onNetworkItemClick = vm::networkClick,
        wifiNetworks = vm.wifiNetworks,
        connectToWifi = vm::connectToWifi,
        getNetworks = vm::getNetworks,
        ssid = { it?.e.orEmpty() },
        signalStrength = { it?.s ?: 0 },
        connectOverBle = vm::connect,
        onBackPress = onBackPress,
        pleaseWait = connectivityLocalization.pleaseWait,
        connect = connectivityLocalization.connect,
        connectDeviceToWiFi = connectivityLocalization.connectDeviceToWiFi,
        refreshNetworks = connectivityLocalization.refreshNetworks,
        ssidText = connectivityLocalization.ssidText,
        password = connectivityLocalization.password,
        releaseToRefresh = connectivityLocalization.releaseToRefresh,
        refreshing = connectivityLocalization.refreshing,
        pullToRefresh = connectivityLocalization.pullToRefresh,
        findDevice = connectivityLocalization.findDevice,
    )
}