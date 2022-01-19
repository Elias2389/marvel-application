package com.ae.marvelapplication.common.connectionchecker

interface CheckConnection {
    /**
     * Check connection
     * @return true when internet connection is available
     */
    suspend fun connectionIsAvailable(): Boolean
}