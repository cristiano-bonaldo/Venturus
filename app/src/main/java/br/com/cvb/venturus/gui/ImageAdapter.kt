package br.com.cvb.venturus.gui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cvb.venturus.R
import br.com.cvb.venturus.model.CatImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_card.view.*

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val ivCard = view.ivCard

    fun bind(catImage: CatImage) {
        Picasso.get().load(catImage.imageLink).fit().centerCrop()
            .placeholder(R.drawable.ic_img_load)
            .error(R.drawable.ic_img_error)
            .into(ivCard)
    }
}

class ImageAdapter(private val listaCatImage: List<CatImage>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imgurImagem = listaCatImage[position]
        holder.bind(imgurImagem)
    }

    override fun getItemCount(): Int = listaCatImage.size
}