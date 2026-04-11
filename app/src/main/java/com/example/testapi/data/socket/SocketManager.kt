package com.example.testapi.data.socket


import android.util.Log
import io.socket.engineio.client.transports.WebSocket
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton
import io.socket.emitter.Emitter

@Singleton
class SocketManager @Inject constructor() {

    private var socket: Socket? = null

    private val _pingFlow = MutableSharedFlow<Int>(extraBufferCapacity = 64)
    val pingFlow = _pingFlow.asSharedFlow()

    fun connect(token: String) {
        if (socket?.connected() == true) return

        val opts = IO.Options().apply {
            transports = arrayOf(WebSocket.NAME)
            query = "jwt=$token"
            reconnection = true
            path = "/socket.io"
        }

        socket = IO.socket("http://10.0.2.2:5000", opts)

        // Универсальный обработчик событий для логов
        val events = listOf(Socket.EVENT_CONNECT, Socket.EVENT_DISCONNECT, Socket.EVENT_CONNECT_ERROR)
        events.forEach { event ->
            socket?.on(event) { Log.d("SOCKET_DEBUG", "System Event: $event") }
        }


        // Основная логика пинга
        socket?.on("ping") { args ->
            try {
                val data = args[0] as JSONObject
                val penpalId = data.getInt("penpal_id")
                Log.d("SOCKET_DEBUG", "Received PING for: $penpalId")

                // Используем emit в scope или tryEmit
                _pingFlow.tryEmit(penpalId)
            } catch (e: Exception) {
                Log.e("SOCKET_DEBUG", "Parse error", e)
            }
        }

        socket?.connect()
    }

    fun disconnect() {
        socket?.off()
        socket?.disconnect()
        socket = null
    }
}