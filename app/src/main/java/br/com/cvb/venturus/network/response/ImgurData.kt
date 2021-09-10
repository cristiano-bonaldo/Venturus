package br.com.cvb.venturus.network.response

import com.google.gson.annotations.SerializedName

data class ImgurData(
    @SerializedName(value = "images")
    val listImage: List<ImgurImage>
)