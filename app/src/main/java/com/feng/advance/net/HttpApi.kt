package com.feng.advance.net

import retrofit2.http.GET

interface HttpApi {

    @GET
    suspend fun getToken(): String

    @GET
    suspend fun getInfo1(token: String): String

    @GET
    suspend fun getInfo2(token: String): String
}