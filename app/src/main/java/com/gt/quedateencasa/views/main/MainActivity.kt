package com.gt.quedateencasa.views.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IntegerRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gt.quedateencasa.R
import com.gt.quedateencasa.views.main.ui.dashboard.DashboardFragment
import com.gt.quedateencasa.views.main.ui.home.HomeFragment
import com.gt.quedateencasa.views.main.ui.notifications.NotificationsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.app_title)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupNavigation()
    }

    fun setupNavigation(){
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_emergency, R.id.navigation_help
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
        nav_view.setOnNavigationItemSelectedListener { item -> onNavigationItemSelected(item) }
    }
    fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.navigation_home -> {
                setFragmentSelected(
                    R.id.navigation_home
                )
                true
            }
            R.id.navigation_emergency -> {
                setFragmentSelected(
                    R.id.navigation_emergency
                )
                true
            }
            R.id.navigation_help -> {
                setFragmentSelected(
                    R.id.navigation_help
                )
                true
            }
            else -> false
        }
        //drawer.closeDrawer(GravityCompat.START)
        return true
    }
    fun setFragmentSelected(fragment:Int) {
        navController.navigate(fragment)
        /*supportActionBar!!.title = title
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment, title)
            .addToBackStack(null).commit()*/
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        return if (id == R.id.action_person) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
