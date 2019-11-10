package com.daya.moviekataloe.view

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.daya.moviekataloe.R
import com.daya.moviekataloe.getCurrentDate
import com.daya.moviekataloe.service.AlarmDailyRepeatingReceiver
import com.daya.moviekataloe.view.adapter.ViewpagerAdapter
import com.daya.moviekataloe.view.search.SearchActivity
import com.daya.moviekataloe.view.settings.SettingsActivity
import com.daya.moviekataloe.view.todayrelease.TodayReleaseActivity
import com.daya.moviekataloe.viewmodel.PreferenceViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    ViewPager.OnPageChangeListener, AnkoLogger {

    private val preferenceViewModel by lazy {
        ViewModelProviders.of(this).get(PreferenceViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when (val position = mainViewpager.currentItem) {
            0 -> setTitleToolbar(position)
            1 -> setTitleToolbar(position)
            2 -> setTitleToolbar(position)
        }

        mainBtmNav.setOnNavigationItemSelectedListener(this)

        val viewpagerAdapter = ViewpagerAdapter(supportFragmentManager)

        mainViewpager.apply {
            adapter = viewpagerAdapter
            currentItem = 0
            addOnPageChangeListener(this@MainActivity)
        }

        if (preferenceViewModel.isFirstRun()) {
            val alarmDaily = AlarmDailyRepeatingReceiver()
            alarmDaily.setAlarmAt7(applicationContext, getCurrentDate())
            alarmDaily.setAlarmAt8(applicationContext, getCurrentDate())
            preferenceViewModel.setFirstRun(false)
        }

        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
            val deviceToken = instanceIdResult.token
            Log.d("tag", "Refreshed token: $deviceToken")
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
            R.id.menu_main_favorit -> {
                mainViewpager.currentItem = 2
                setTitleToolbar(2)
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
            2 -> {
                mainBtmNav.selectedItemId = R.id.menu_main_favorit
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
            R.id.menu_main_search -> {
                startActivity<SearchActivity>()
            }
            R.id.menu_main_settings -> {
                startActivity<SettingsActivity>()
            }
            R.id.menu_main_today_release -> {
                startActivity<TodayReleaseActivity>()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setTitleToolbar(position: Int) {
        when (position) {
            0 -> supportActionBar?.title = getString(R.string.movie)
            1 -> supportActionBar?.title = getString(R.string.tv)
            2 -> supportActionBar?.title = getString(R.string.favorite)
        }
    }
}
