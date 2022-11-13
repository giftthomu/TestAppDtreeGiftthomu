package com.giftthomu.DtreeTestApp.services


import com.giftthomu.DtreeTestApp.model.Member
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("group-1")
    fun getAllMembers(): Call<List<Member>>
}