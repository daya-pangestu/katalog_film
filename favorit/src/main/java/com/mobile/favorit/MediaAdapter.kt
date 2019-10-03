package com.mobile.favorit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_favorite.*

class MediaAdapter(private val TYPE: String) : RecyclerView.Adapter<MediaAdapter.ItemHolder>() {

    var listMovie: List<MovieFavModel>? = null
    var listTv: List<TvFavModel>? = null

    companion object {
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w185"

        const val TYPE_FAV_MOVIE = "fav_movie"
        const val TYPE_FAV_TV = "fav_tv"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)

        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        when (TYPE) {
            TYPE_FAV_MOVIE -> {
                val movieItem = listMovie?.get(position)
                movieItem?.let { holder.bindMovie(it) }
            }
            TYPE_FAV_TV -> {
                val tvItem = listTv?.get(position)
                tvItem?.let { holder.bindTv(it) }
            }
        }
    }


    override fun getItemCount(): Int {
        return when (TYPE) {
            TYPE_FAV_MOVIE -> {
                listMovie?.size ?: 0
            }
            TYPE_FAV_TV -> {
                listTv?.size ?: 0
            }
            else -> 0
        }
    }


    inner class ItemHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!), LayoutContainer {


        fun bindMovie(movie: MovieFavModel) {
            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + movie.imageLink)
                    .into(itemImgPoster)

                itemTxtJudul.text = movie.title
                itemTxtDesc.text = movie.desc
            }
        }

        fun bindTv(tv: TvFavModel) {
            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + tv.imageLink)
                    .into(itemImgPoster)

                itemTxtJudul.text = tv.title
                itemTxtDesc.text = tv.desc


            }

        }
    }
}