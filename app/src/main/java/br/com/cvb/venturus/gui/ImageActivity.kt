package br.com.cvb.venturus.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import br.com.cvb.venturus.R
import br.com.cvb.venturus.model.CatImage
import br.com.cvb.venturus.model.StatusTela
import kotlinx.android.synthetic.main.activity_main.*

class ImageActivity : AppCompatActivity() {
    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle(R.string.app_title)

        viewModel = ViewModelProvider(this).get(ImageViewModel::class.java)

        setModelViewObservers()

        viewModel.getImages()
    }

    private fun setModelViewObservers() {
        viewModel.statusTelaLiveData.observe(this, {
            it?.let { statusTela ->
                configTela(statusTela)
            }
        })

        viewModel.listImagesLiveData.observe(this, {
            it?.let { listCatImage ->
                loadGallery(listCatImage)
            }
        })
    }

    private fun loadGallery(listaCatImage: List<CatImage>) {
        val imageAdapter = ImageAdapter(listaCatImage)
        val gridLayoutManager = GridLayoutManager(this, 3)

        with(recycler) {
            layoutManager = gridLayoutManager
            adapter = imageAdapter
        }
    }

    private fun configTela(statusTela: StatusTela) {
        var msg = ""
        if (statusTela.idMsgErro >= 0) {
            msg = getString(statusTela.idMsgErro)
            if (statusTela.msgAux != null)
                msg = msg + "\n" + statusTela.msgAux
        }

        tvInfo.text = msg
        tvInfo.visibility = if (statusTela.status != StatusTela.C_STATUS_OK) View.VISIBLE else View.GONE

        progBar.visibility = if (statusTela.status == StatusTela.C_STATUS_LOADING) View.VISIBLE else View.GONE

        recycler.visibility = if (statusTela.status == StatusTela.C_STATUS_OK) View.VISIBLE else View.GONE
    }
}