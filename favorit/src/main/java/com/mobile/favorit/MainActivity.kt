package com.mobile.favorit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)


        /*   supportFragmentManager
               .beginTransaction()
               .add(
                   R.id.frame_container,
                   ContainerFragment(),
                   ContainerFragment::class.java.simpleName
               )
               .commit()
   */

        val fragmentAdapter = FavoriteAdapter(
            this,
            supportFragmentManager,
            arrayListOf(movieFavFragment(), tvFavFragment())
        )
        viewPager.adapter = fragmentAdapter
        toolbar.setViewPager(viewPager)
    }
}