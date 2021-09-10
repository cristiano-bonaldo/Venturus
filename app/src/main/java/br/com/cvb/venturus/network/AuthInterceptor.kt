package br.com.cvb.venturus.network

import okhttp3.Interceptor
import okhttp3.Response


private const val C_HEADER_AUTH: String = "Authorization";
private const val C_CLIENT_ID: String = "Client-ID 1ceddedc03a5d71"

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder();

        requestBuilder.addHeader(C_HEADER_AUTH, C_CLIENT_ID);

        return chain.proceed(requestBuilder.build());
    }
}