package com.daya.moviekataloe.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.daya.moviekataloe.R
import com.daya.moviekataloe.loadImageWithGlide
import com.daya.moviekataloe.repo.room.MovieFavTable
import com.daya.moviekataloe.repo.room.TvFavTable
import com.daya.moviekataloe.viewmodel.FavoriteViewModel
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"

    }

    private lateinit var source: String
    var movie: MovieFavTable? = null
    var tv: TvFavTable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val viewModel by lazy { ViewModelProviders.of(this).get(FavoriteViewModel::class.java) }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail)
        }
        movie =
            intent.getParcelableExtra(EXTRA_MOVIE)
        tv = intent.getParcelableExtra(EXTRA_TV)

        when {
            movie != null -> {
                detailTxtJudul.text = movie?.title
                detailTxtDesc.text = movie?.description
                detailImgPoster.loadImageWithGlide(movie?.imageLink, context = this@DetailActivity)
                source = EXTRA_MOVIE

            }
            tv != null -> {
                detailTxtJudul.text = tv?.title
                detailTxtDesc.text = tv?.description
                detailImgPoster.loadImageWithGlide(movie?.imageLink, context = this@DetailActivity)

                source = EXTRA_TV
            }
        }

        when (source) {
            EXTRA_MOVIE -> {
                viewModel.isMovieFavorite(movie?.id!!).observe(this, Observer {
                    detailFavMov.isLiked = it != null
                })

                detailFavMov.setOnLikeListener(object : OnLikeListener {
                    override fun liked(likeButton: LikeButton?) {
                        movie?.let {
                            viewModel.addFavoriteMovie(it)
                            it.title?.let { it1 -> toastDetail(it1, true) }
                        }
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        movie?.let {
                            viewModel.deleteFavoriteMovie(it)
                            it.title?.let { it1 -> toastDetail(it1, false) }

                        }
                    }
                })
            }
            EXTRA_TV -> {

                viewModel.isTvFavorite(tv?.id!!).observe(this, Observer {
                    detailFavMov.isLiked = it != null
                })

                detailFavMov.setOnLikeListener(object : OnLikeListener {
                    override fun liked(likeButton: LikeButton?) {
                        tv?.let {
                            viewModel.addFavoriteTv(it)
                            toastDetail(it.title, true)
                        }
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        tv?.let {
                            viewModel.deleteFavoriteTv(it)
                            toastDetail(it.title, false)
                        }
                    }
                })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun toastDetail(text: String, setLiked: Boolean) {
        when (setLiked) {
            true -> Toast.makeText(
                this@DetailActivity,
                text + getString(R.string.added_to_favorite),
                Toast.LENGTH_SHORT
            ).show()
            false -> Toast.makeText(
                this@DetailActivity,
                text + getString(R.string.removed_from_favorite),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        movie = null
        tv = null
    }
}
