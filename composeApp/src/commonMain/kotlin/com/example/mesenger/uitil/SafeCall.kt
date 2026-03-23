package com.example.mesenger.uitil

import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import util.NetworkError

suspend inline fun <reified T> safeCall(
    crossinline execute: suspend () -> HttpResponse
): Result<T, NetworkError> {

    val response = try {
        execute()
    } catch (e: HttpRequestTimeoutException) {
        return Result.Error(NetworkError.REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: IOException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: Exception) {
        return Result.Error(NetworkError.UNKNOWN)
    }

    return when (response.status.value) {
        in 200..299 -> {
            try {
                val body = response.body<T>()
                Result.Success(body)
            } catch (e: Exception) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }

        401 -> Result.Error(NetworkError.UNAUTHORIZED)
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        409 -> Result.Error(NetworkError.CONFLICT)
        413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}