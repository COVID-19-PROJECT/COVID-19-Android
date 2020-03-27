package com.gt.quedateencasa.views.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gt.quedateencasa.R
import com.gt.quedateencasa.models.User.UserObject
import com.gt.quedateencasa.models.User.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.popup_complete_profile.view.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val userViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    private var dateFormat = "dd/MM/yyyy"
    private lateinit var calendar: Calendar
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.app_title)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setupNavigation()
        showCompleteProfile()
    }

    //Components configuration
    private fun setupNavigation() {
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

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
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
        return true
    }

    private fun setFragmentSelected(fragment: Int) {
        navController.navigate(fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.itemId
        return if (id == R.id.action_person) {
            true
        } else super.onOptionsItemSelected(item)
    }

    //Double tap to exit
    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.finish()
            System.exit(0)
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(
            this,
            resources.getText(R.string.message_doble_press_back),
            Toast.LENGTH_SHORT
        ).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    //Dialog configuration and functions
    private fun showCompleteProfile() {
        calendar = Calendar.getInstance()
        val profileView =
            LayoutInflater.from(applicationContext).inflate(R.layout.popup_complete_profile, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(profileView)
        val profileDialog = builder.show();
        profileDialog.setCancelable(false)

        profileView.button_save.setOnClickListener {
            val gender =
                if (profileView.gender_selector.text.isNotEmpty()) profileView.gender_selector.text[0] else null
            val bornDate = calendar.time
            if (gender != null) {
                profileView.layout_progressbar.visibility = View.VISIBLE;
                userViewModel.saveUserData(
                    context = applicationContext,
                    id = "id_user",
                    data = UserObject(
                        id = "id_user",
                        firstname = "",
                        surname = "",
                        gender = gender,
                        bornDate = bornDate
                    )
                )
                    .observe(this, Observer { value ->
                        profileView.layout_progressbar.visibility = View.GONE
                        profileDialog.dismiss()
                    })
            } else Toast.makeText(
                applicationContext,
                resources.getString(R.string.verify_user_data),
                Toast.LENGTH_SHORT
            ).show()
        }
        profileView.textview_later.setOnClickListener {
            profileDialog.dismiss()
        }
        profileView.gender_selector.setOnClickListener { view ->
            selectGender(view as TextView)
        }
        profileView.born_selector.setOnClickListener { view ->
            selectBornDate(view as TextView)
        }
    }

    private fun selectGender(view: TextView) {
        val options =
            resources.getStringArray(R.array.genders)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.gender))
        builder.setItems(options) { dialog, which ->
            view.text = options[which]
        }
        builder.show()
    }

    private fun selectBornDate(textView: TextView) {
        val sdf = SimpleDateFormat(dateFormat, Locale.US)
        val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            textView.text = sdf.format(calendar.time)
        }
        DatePickerDialog(
            this,
            listener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}
