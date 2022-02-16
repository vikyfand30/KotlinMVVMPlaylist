package com.example.femaledailytest

import java.lang.Exception

class NetworkException(override val message: String?) : Exception()
class ServerException(override val message: String?) : Exception()