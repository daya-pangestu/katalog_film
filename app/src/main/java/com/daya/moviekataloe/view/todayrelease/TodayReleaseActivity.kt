package com.daya.moviekataloe.view.todayrelease

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.view.adapter.MediaAdapter
import com.daya.moviekataloe.viewmodel.TodayReleaseViewModel
import kotlinx.android.synthetic.main.activity_today_release.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodayReleaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_release)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Today Released Movie"
        }

        val todayViewModel by lazy {
            ViewModelProviders.of(this).get(TodayReleaseViewModel::class.java)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val movie = todayViewModel.getMovieToday()
            movie?.let {
                initRecyclerview(it, this@TodayReleaseActivity)
            }
        }
    }

    private suspend fun initRecyclerview(movieModel: MovieModel, context: Context) {
        withContext(Dispatchers.Main) {
        val movieAdapter = MediaAdapter(MediaAdapter.TYPE_MOVIE)
        movieAdapter.movieModel = movieModel
        actTodayMovieRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
            actTodayProgressBar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
