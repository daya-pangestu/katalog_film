package com.daya.moviekataloe.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.daya.moviekataloe.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val fragmentmovie : MovieFragment by lazy { MovieFragment() }
    private val fragmentTv : TvFragment by lazy{ TvFragment() }
    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }
    private var activeFragment : Fragment = fragmentmovie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){

            fragmentManager.beginTransaction()
                .add(R.id.mainFrame, fragmentTv).hide(fragmentTv).commit()

            fragmentManager.beginTransaction()
                .add(R.id.mainFrame, fragmentmovie).commit()

            mainBtmNav.setOnNavigationItemSelectedListener(this)

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_movie ->{
                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentmovie).commit()
                activeFragment = fragmentmovie
                true
            }
            R.id.menu_main_tv -> {
                fragmentManager.beginTransaction().hide(activeFragment).show(fragmentTv).commit()
                activeFragment = fragmentTv
                true
            }
            else -> false
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
}

















