package uz.gita.bookappwithflow.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.bookappwithflow.app.App

object ApiClient {


    val myClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.appContext)
            .build())


    val retrofit = Retrofit.Builder()
        .baseUrl("http://143.198.48.222:82/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(myClient.build())
        .build()

}