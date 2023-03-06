package com.programmersbox.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener

@Composable
public actual fun WiFiDiscoveryScreen(
    onConnect: (url: String) -> Unit,
    openBLEDiscovery: () -> Unit,
    onBackPress: () -> Unit,
    connectivityLocalization: ConnectivityLocalization
) {
    val scope = rememberCoroutineScope()
    DiscoverWiFiScreen(
        discover = { list, isSearching ->
            isSearching(true)
            scope.launch(Dispatchers.IO) {
                val jmdns = JmDNS.create(InetAddress.getByName("10.0.0.2"), "HOST")
                jmdns.addServiceListener(
                    "_http._tcp.local.",
                    object : ServiceListener {
                        override fun serviceAdded(event: ServiceEvent) {
                        }

                        override fun serviceRemoved(event: ServiceEvent) {
                        }

                        override fun serviceResolved(event: ServiceEvent) {
                            list.addAll(
                                event.info.inet4Addresses.mapNotNull {
                                    it.hostAddress?.let { it1 -> DeviceIp(it1, it.hostName) }
                                }
                            )
                        }
                    }
                )
                delay(30000)
                jmdns.unregisterAllServices()
                isSearching(false)
            }
        },
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