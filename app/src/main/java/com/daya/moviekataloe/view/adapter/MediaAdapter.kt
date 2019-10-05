package com.daya.moviekataloe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.model.tv.TvModel
import com.daya.moviekataloe.repo.room.MovieFavTable
import com.daya.moviekataloe.repo.room.TvFavTable
import com.daya.moviekataloe.view.DetailActivity
import com.daya.moviekataloe.view.DetailActivity.Companion.EXTRA_MOVIE
import com.daya.moviekataloe.view.DetailActivity.Companion.EXTRA_TV
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import org.jetbrains.anko.startActivity


class MediaAdapter(private var TYPE: String?) : RecyclerView.Adapter<MediaAdapter.ItemHolder>() {


    companion object {
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w185"

        const val TYPE_MOVIE = "movie"
        const val TYPE_TV = "tv"
        const val TYPE_FAV_MOVIE = "fav_movie"
        const val TYPE_FAV_TV = "fav_tv"
    }

    var movieModel: MovieModel? = null
    var tvModel: TvModel? = null
    var movFavModel: List<MovieFavTable>? = null
    var tvFavModel: List<TvFavTable>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {

        when (TYPE) {
            TYPE_MOVIE -> {
                val movieItem = movieModel?.results
                holder.bindMovie(movieItem!![position])
            }
            TYPE_TV -> {
                val tvItem = tvModel?.results
                holder.bindTv(tvItem!![position])
            }
            TYPE_FAV_MOVIE -> {
                val movieFavItem = movFavModel?.get(position)
                movieFavItem?.let { holder.bindFavMovie(it) }
            }
            TYPE_FAV_TV -> {
                val movieFavItem = tvFavModel?.get(position)
                movieFavItem?.let { holder.bindFavTv(it) }
            }
        }

    }

    override fun getItemCount(): Int {
        return when (TYPE) {
            TYPE_MOVIE -> {
                movieModel?.results?.size ?: 0
            }
            TYPE_TV -> {
                tvModel?.results?.size ?: 0
            }
            TYPE_FAV_MOVIE -> {
                movFavModel?.size ?: 0
            }
            TYPE_FAV_TV -> {
                tvFavModel?.size ?: 0
            }
            else -> 0
        }
    }

    fun clear() {
        if (movieModel != null) movieModel = null
        else tvModel = null
        notifyItemRangeRemoved(0, itemCount)
    }

    inner class ItemHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!), LayoutContainer {

        fun bindMovie(result: com.daya.moviekataloe.model.movie.Result) {

            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + result.poster_path)
                    .into(itemImgPoster)
            }

            fun intentContent(view: View) {
                view.context.startActivity<DetailActivity>(
                    EXTRA_MOVIE to MovieFavTable(
                        result.id,
                        result.title,
                        result.overview,
                        result.poster_path
                    )
                )
            }

            itemTxtJudul.text = result.title
            itemTxtDesc.text = result.overview

            itemView.setOnClickListener {
                intentContent(it)
            }

            itemBtnDetail.setOnClickListener {
                intentContent(it)
            }
        }

        fun bindTv(result: com.daya.moviekataloe.model.tv.Result) {
            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + result.poster_path)
                    .into(itemImgPoster)
            }

            itemTxtJudul.text = result.name
            itemTxtDesc.text = result.overview

            fun intentCOntent(view: View) {
                view.context.startActivity<DetailActivity>(
                    EXTRA_TV to TvFavTable(
                        result.id,
                        result.name,
                        result.overview,
                        result.poster_path
                    )
                )
            }

            itemView.setOnClickListener { view ->
                intentCOntent(view)
            }

            itemBtnDetail.setOnClickListener { view ->
                intentCOntent(view)
            }

        }

        fun bindFavMovie(movie: MovieFavTable) {
            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + movie.imageLink)
                    .into(itemImgPoster)

                itemTxtJudul.text = movie.title
                itemTxtDesc.text = movie.description

                fun intentContent(view: View) {
                    view.context.startActivity<DetailActivity>(EXTRA_MOVIE to movie)
                }

                itemView.setOnClickListener { view ->
                    intentContent(view)
                }
                itemBtnDetail.setOnClickListener { view ->
                    intentContent(view)
                }
            }

        }

        fun bindFavTv(tv: TvFavTable) {
            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + tv.imageLink)
                    .into(itemImgPoster)

                itemTxtJudul.text = tv.title
                itemTxtDesc.text = tv.description


                fun intentContent(view: View) {
                    view.context.startActivity<DetailActivity>(EXTRA_TV to tv)

                }

                itemView.setOnClickListener { view ->
                    intentContent(view)
                }

                itemBtnDetail.setOnClickListener { view ->
                    intentContent(view)
                }
            }
        }
    }
}
