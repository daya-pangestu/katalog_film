package com.daya.moviekataloe.view.search

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.model.tv.TvModel
import com.daya.moviekataloe.view.adapter.MediaAdapter
import com.daya.moviekataloe.viewmodel.SearchViewModel
import com.paulrybitskyi.persistentsearchview.listeners.OnSearchConfirmedListener
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_search.*
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val QUERY_BY_MOVIE = 1
        const val QUERY_BY_TV = 2
    }

    private var QUERY_FILTER = QUERY_BY_MOVIE

    private val searchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        with(persistentSearchView) {
            setOnLeftBtnClickListener(this@SearchActivity)
            setOnRightBtnClickListener(this@SearchActivity)
            setQueryInputHint(getString(R.string.search_hint_movie))
            showRightButton()
            setDismissOnTouchOutside(true)
            setDimBackground(true)
            isProgressBarEnabled = true
            isVoiceInputButtonEnabled = false
            isClearInputButtonEnabled = true
            setQueryInputGravity(Gravity.START or Gravity.CENTER)

            setOnSearchConfirmedListener(onSearchConfirmedListener)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search_toolbar, menu)
        return true

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.leftBtnIv -> {
                finish()
            }
            R.id.rightBtnIv -> {
                MaterialDialog(this).show {
                    titleColor = R.color.primaryTextColor
                    title = getString(R.string.dialog_filter_search_title)
                    listItemsSingleChoice(R.array.array_choose_search_type) { _, index, _ ->
                        if (index == 0) {
                            toast(getString(R.string.hint_search_toast_movie))
                            setOnDismissListener {
                                this@SearchActivity.persistentSearchView.apply {
                                    setQueryInputHint(
                                        getString(R.string.search_hint_movie)
                                    )
                                    inputQuery = ""
                                    QUERY_FILTER = QUERY_BY_MOVIE
                                    this@SearchActivity.layoutSearchText.apply {
                                        visibility = View.VISIBLE
                                        text = getString(R.string.search_hint_movie)
                                    }

                                    // initRecyclerviewMovie(null)
                                    this@SearchActivity.actSearchRecyclerview.visibility = View.GONE
                                }
                            }
                        } else {
                            toast(getString(R.string.hint_search_toast_tv))
                            setOnDismissListener {
                                this@SearchActivity.persistentSearchView.apply {
                                    setQueryInputHint(
                                        getString(R.string.search_hint_tv)
                                    )
                                    inputQuery = ""
                                    QUERY_FILTER = QUERY_BY_TV
                                    this@SearchActivity.layoutSearchText.apply {
                                        visibility = View.VISIBLE
                                        text = getString(R.string.search_hint_tv)
                                    }
                                    //  initRecyclerviewTv(null)
                                    this@SearchActivity.actSearchRecyclerview.visibility = View.GONE
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private val onSearchConfirmedListener =
        OnSearchConfirmedListener { searchView, query ->
            searchView.collapse()
            actSearchlayoutSearch.visibility = View.GONE
            actSearchRecyclerview.visibility = View.VISIBLE

            searchView.apply {
                showProgressBar()
                hideLeftButton()
            }
            when (QUERY_FILTER) {
                QUERY_BY_MOVIE -> {
                    searchViewModel.getMoviBysearch(query).observe(this, Observer { movie ->
                        if (movie != null) {
                            initRecyclerviewMovie(movie)
                            searchView.apply {
                                hideProgressBar()
                                showLeftButton()
                            }
                            actSearchlayoutSearch.visibility = View.GONE

                        } else {
                            initRecyclerviewMovie(movie)
                            searchView.apply {
                                hideProgressBar()
                                showLeftButton()
                            }
                            toast(getString(R.string.result_empty))
                            actSearchlayoutSearch.visibility = View.VISIBLE
                        }
                    })
                }
                QUERY_BY_TV -> {
                    searchViewModel.getTvBySearch(query).observe(this, Observer { tvModel ->
                        if (tvModel != null) {
                            initRecyclerviewTv(tvModel)
                            searchView.apply {
                                hideProgressBar()
                                showLeftButton()
                            }
                            actSearchlayoutSearch.visibility = View.GONE
                        } else {
                            initRecyclerviewTv(tvModel)
                            searchView.apply {
                                hideProgressBar()
                                showLeftButton()
                            }
                            toast(getString(R.string.result_empty))
                            actSearchlayoutSearch.visibility = View.VISIBLE
                        }
                    })
                }
            }
        }

    private fun initRecyclerviewMovie(movieModel: MovieModel?) {
        val movieAdapter = MediaAdapter(MediaAdapter.TYPE_MOVIE)
        movieAdapter.movieModel = movieModel
        actSearchRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initRecyclerviewTv(tvModel: TvModel?) {
        val movieAdapter = MediaAdapter(MediaAdapter.TYPE_TV)
        movieAdapter.tvModel = tvModel
        actSearchRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}