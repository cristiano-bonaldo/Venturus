package br.com.cvb.venturus.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val C_URL = "https://api.imgur.com/3/"

class RetrofitConfig {
    private companion object {
        fun getRetrofitInstance() : Retrofit {

            val authInterceptor = AuthInterceptor()

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(C_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
    }

    fun getAPIService(): APIService = getRetrofitInstance().create(APIService::class.java)
}
