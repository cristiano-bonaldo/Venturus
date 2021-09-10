package br.com.cvb.venturus.network.response

import com.google.gson.annotations.SerializedName

data class ImgurResponse(
    @SerializedName(value = "data")
    val listData: List<ImgurData>
)
