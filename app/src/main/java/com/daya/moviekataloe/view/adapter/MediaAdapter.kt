package com.daya.moviekataloe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.daya.moviekataloe.model.MediaModel
import com.daya.moviekataloe.R
import com.daya.moviekataloe.view.DetailActivity
import com.daya.moviekataloe.view.DetailActivity.Companion.EXTRA_MOVIE
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import org.jetbrains.anko.startActivity

class MediaAdapter() : RecyclerView.Adapter<MediaAdapter.ItemHolder>() {
    var mediaList: ArrayList<MediaModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        mediaList?.let {
            val currentMovie = it[position]
            holder.bind(currentMovie)
        }
    }

    override fun getItemCount(): Int = mediaList?.size ?: 0

    inner class ItemHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer{

        fun bind(media: MediaModel) {
            containerView?.let {
                itemImgPoster.setImageDrawable(ContextCompat.getDrawable(it.context, media.poster))
            }
            itemTxtJudul.text = media.judul.toString()
            itemTxtDesc.text = media.deskripsi

            itemView.setOnClickListener { view ->
                view.context.startActivity<DetailActivity>(EXTRA_MOVIE to media)
            }
        }
    }
}
