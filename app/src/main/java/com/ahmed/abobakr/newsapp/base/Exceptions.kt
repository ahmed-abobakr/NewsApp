package com.ahmed.abobakr.newsapp.base

import retrofit2.HttpException

public class ApiException(override val message: String, val errorCode: Int = 0) : Exception(message)

public class NetowrkException(message: String): Exception(message)

public class AuthException(override val message: String): Exception(message)

public class UnknownException(val throwable: Throwable): Exception(throwable)