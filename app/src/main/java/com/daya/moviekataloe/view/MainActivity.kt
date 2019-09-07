package com.daya.moviekataloe.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.daya.moviekataloe.R
import com.daya.moviekataloe.view.adapter.ViewpagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (val position = mainViewpager.currentItem) {
            0 -> setTitleToolbar(position)
            1 -> setTitleToolbar(position)
        }

        mainBtmNav.setOnNavigationItemSelectedListener(this)

        val viewpagerAdapter = ViewpagerAdapter(supportFragmentManager)
        viewpagerAdapter.apply {
            fragment.add(MovieFragment())
            fragment.add(TvFragment())
        }

        mainViewpager.apply {
            adapter = viewpagerAdapter
            currentItem = 0
            addOnPageChangeListener(this@MainActivity)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_movie -> {
                mainViewpager.currentItem = 0
                setTitleToolbar(0)
                true
            }
            R.id.menu_main_tv -> {
                mainViewpager.currentItem = 1
                setTitleToolbar(1)
                true
            }
            else -> false
        }
    }

    override fun onPageScrollStateChanged(state: Int) {}
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                mainBtmNav.selectedItemId = R.id.menu_main_movie
                setTitleToolbar(position)
            }
            1 -> {
                mainBtmNav.selectedItemId = R.id.menu_main_tv
                setTitleToolbar(position)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main_language -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTitleToolbar(position: Int) {
        when (position) {
            0 -> supportActionBar?.title = getString(R.string.movie)
            1 -> supportActionBar?.title = getString(R.string.tv)
        }
    }
}
