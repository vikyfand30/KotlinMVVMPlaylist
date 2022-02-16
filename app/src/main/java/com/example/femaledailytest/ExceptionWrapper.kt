package com.example.femaledailytest

//Add more custom exception
sealed class ExceptionWrapper : Exception() {
    object NoNetworkException : ExceptionWrapper()
    data class RequestException(override val message: String) : ExceptionWrapper()
}
