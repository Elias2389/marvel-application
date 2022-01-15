package com.ae.marvelapplication.util


import java.io.InputStreamReader

object JsonReaderUtil {
    fun readJsonFromFile(jsonFile: String): String {
        val fileRead = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(jsonFile))
        val jsonString = fileRead.readText()
        fileRead.close()
        return jsonString
    }
}



