package br.com.cvb.venturus.network

import br.com.cvb.venturus.network.response.ImgurResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("gallery/search/?q=cats")
    fun getGalleryCatsImages(): Call<ImgurResponse>
}
