package com.ae.data.connectionchecker

interface CheckConnection {
    /**
     * Check connection
     * @return true when internet connection is available
     */
    suspend fun connectionIsAvailable(): Boolean
}