package com.ae.marvelapplication.common.connectionchecker

interface CheckConnection {
    /**
     * Check connection
     * @return true when connection is available and
     * false when it isn't
     */
    suspend fun connectionIsAvailable(): Boolean
}