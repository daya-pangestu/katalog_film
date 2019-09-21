package com.daya.moviekataloe.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.daya.moviekataloe.R
import com.daya.moviekataloe.view.adapter.MediaAdapter.Companion.BASE_URL_IMAGE
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail)
        }
        val movie: com.daya.moviekataloe.model.movie.Result? =
            intent.getParcelableExtra(EXTRA_MOVIE)
        val tv: com.daya.moviekataloe.model.tv.Result? = intent.getParcelableExtra(EXTRA_TV)


        if (movie != null) {
            detailTxtJudul.text = movie.title
            detailTxtDesc.text = movie.overview
            Glide.with(this).load(BASE_URL_IMAGE + movie.poster_path).into(detailImgPoster)
        } else if (tv != null) {
            detailTxtJudul.text = tv.name
            detailTxtDesc.text = tv.overview
            Glide.with(this).load(BASE_URL_IMAGE + tv.poster_path).into(detailImgPoster)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
