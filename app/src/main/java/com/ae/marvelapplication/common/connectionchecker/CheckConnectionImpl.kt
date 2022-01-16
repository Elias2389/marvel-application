package com.ae.marvelapplication.common.connectionchecker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class to check internet connection
 */
@Singleton
class CheckConnectionImpl @Inject constructor() : CheckConnection {

    override suspend fun connectionIsAvailable(): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            val sock = Socket()
            val socketAddress = InetSocketAddress(HOSTNAME, PORT)
            sock.connect(socketAddress, TIMEOUT)
            sock.close()
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private companion object {
        const val HOSTNAME: String = "8.8.8.8"
        const val PORT: Int = 53
        const val TIMEOUT: Int = 1500
    }
}
