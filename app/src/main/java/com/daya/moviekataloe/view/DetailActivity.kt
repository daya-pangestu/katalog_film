package com.daya.moviekataloe.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.MediaModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.detail)
        }

        val movie = intent.getParcelableExtra<MediaModel>(EXTRA_MOVIE)

        movie?.let {
            detailImgPoster.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, it.poster))
            detailTxtDesc.text = it.deskripsi
            detailTxtJudul.text = it.judul
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
