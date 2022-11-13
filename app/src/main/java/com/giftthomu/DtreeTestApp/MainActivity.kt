package com.giftthomu.DtreeTestApp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.giftthomu.DtreeTestApp.model.Member
import com.giftthomu.DtreeTestApp.services.ApiService
import com.giftthomu.DtreeTestApp.services.ServiceBuilder

import com.giftthomu.DtreeTestApp.ui.RecordsScreen
import com.giftthomu.DtreeTestApp.ui.theme.SearchBarTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ///Creating an instance of the service builder to get the Data from the API
        val APIService : ApiService = ServiceBuilder.buildService(ApiService::class.java)
        val requestCall : Call<List<Member>> = APIService.getAllMembers()

        requestCall.enqueue(object : Callback<List<Member>> {
            override fun onResponse(call: Call<List<Member>>, response: Response<List<Member>>) {
                if (response.isSuccessful){
                    val memberList : List<Member> = response.body()!!
                    Log.d("Our Members" , "$memberList")
                }else
                    Log.d("Failure" , "Failed")
            }
            override fun onFailure(call: Call<List<Member>>, t: Throwable) {
                Log.d("Failure" , "cant load data")
            }
        })


        setContent {
            SearchBarTheme {
                val viewModel = viewModel<RecordsViewModel>()
                RecordsScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}

val recordsList = listOf(
    "giftthomu",
    "giftthomu",
    "giftthomu",
    "catherinethomu"

)