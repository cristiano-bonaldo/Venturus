package br.com.cvb.venturus.gui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.cvb.venturus.R
import br.com.cvb.venturus.model.CatImage
import br.com.cvb.venturus.model.StatusTela
import br.com.cvb.venturus.network.RetrofitConfig
import br.com.cvb.venturus.network.response.ImgurResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageViewModel : ViewModel() {

    val listImagesLiveData: MutableLiveData<List<CatImage>> = MutableLiveData()
    var statusTelaLiveData: MutableLiveData<StatusTela> = MutableLiveData()

    fun getImages() {
        statusTelaLiveData.value = StatusTela(StatusTela.C_STATUS_LOADING, R.string.status_load)

        getListImageFromAPI()
    }

    private fun getListImageFromAPI() {
        val apiService = RetrofitConfig().getAPIService()

        val call = apiService.getGalleryCatsImages()

        call.enqueue(object : Callback<ImgurResponse> {
            override fun onResponse(call: Call<ImgurResponse>, response: Response<ImgurResponse>) {
                if (response.isSuccessful)
                    processaJSON(response.body())
                else
                    statusTelaLiveData.value =
                        StatusTela(StatusTela.C_STATUS_ERRO, R.string.status_erro_app)
            }

            override fun onFailure(call: Call<ImgurResponse>, t: Throwable) {
                statusTelaLiveData.value =
                    StatusTela(StatusTela.C_STATUS_ERRO, R.string.status_erro_serv,t.message ?: "")
            }
        })
    }

    private fun processaJSON(imgurResponse: ImgurResponse?) {
        val listaCatImage = arrayListOf<CatImage>()

        val listD = imgurResponse?.listData

        if (listD != null && listD.isNotEmpty()) {
            for (imgurData in listD) {
                val listaI = imgurData.listImage

                if (listaI != null && listaI.isNotEmpty()) {
                    val listaImagemJpegPng =
                        listaI.filter { img ->
                            img.type == "image/jpeg" ||
                            img.type == "image/png"
                        }

                    listaImagemJpegPng.forEach { imgurImage ->
                        listaCatImage.add(
                            CatImage(imgurImage.link)
                        )
                    }
                }
            }
        }

        listImagesLiveData.value = listaCatImage

        statusTelaLiveData.value = if (listaCatImage.size > 0)
            StatusTela(StatusTela.C_STATUS_OK) else
            StatusTela(StatusTela.C_STATUS_EMPTY, R.string.status_empty)
    }
}