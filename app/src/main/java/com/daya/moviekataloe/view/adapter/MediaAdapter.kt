package com.daya.moviekataloe.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.model.tv.TvModel
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
    }

    var movieModel: MovieModel? = null
    var tvModel: TvModel? = null

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
        }

    }

    override fun getItemCount(): Int {
        return if (TYPE == TYPE_MOVIE) {
            movieModel?.results?.size ?: 0
        } else {
            tvModel?.results?.size ?: 0
        }
    }

    inner class ItemHolder(override val containerView: View?) :
        RecyclerView.ViewHolder(containerView!!), LayoutContainer {

        fun bindMovie(result: com.daya.moviekataloe.model.movie.Result) {

            containerView?.let {
                Glide.with(it)
                    .load(BASE_URL_IMAGE + result.poster_path)
                    .into(itemImgPoster)
            }

            itemTxtJudul.text = result.title
            itemTxtDesc.text = result.overview

            itemView.setOnClickListener { view ->
                view.context.startActivity<DetailActivity>(EXTRA_MOVIE to result)
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

            itemView.setOnClickListener { view ->
                if (result.poster_path.isEmpty() || result.name.isEmpty() || result.overview.isEmpty()) {
                    Toast.makeText(
                        view.context.applicationContext, "data error try another",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    view.context.startActivity<DetailActivity>(EXTRA_TV to result)
                }
            }
        }
    }
}
