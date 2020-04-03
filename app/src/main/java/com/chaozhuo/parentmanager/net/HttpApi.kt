package com.chaozhuo.parentmanager.net

import android.view.ViewGroup
import retrofit2.http.GET

interface HttpApi {

    @GET
    suspend fun getToken(v:ViewGroup): String
    @GET
    suspend fun getInfo1(token: String):String
    @GET
    suspend fun getInfo2(token: String):String
}