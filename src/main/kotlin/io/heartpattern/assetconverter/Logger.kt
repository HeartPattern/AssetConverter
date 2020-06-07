package io.heartpattern.assetconverter

interface Logger {
    fun debug(message: String)
    fun info(message: String)
    fun warn(message: String)
}

class ConsoleLogger(
    val isDebugEnabled: Boolean = false,
    val isInfoEnabled: Boolean = true,
    val isWarnEnabled: Boolean = true
) : Logger {
    override fun debug(message: String) {
        if(isDebugEnabled)
            println("DEBUG) $message")
    }

    override fun info(message: String) {
        if(isInfoEnabled)
            println("INFO) $message")
    }

    override fun warn(message: String) {
        if(isWarnEnabled)
            println("WARN) $message")
    }
}