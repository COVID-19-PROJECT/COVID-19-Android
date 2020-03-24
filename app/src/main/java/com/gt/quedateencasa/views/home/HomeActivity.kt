package com.gt.quedateencasa.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.gt.quedateencasa.R

class HomeActivity : AppCompatActivity() {

    var facebookButton: Button? = null
    var twitterButton: Button? = null
    var googleButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        facebookButton = findViewById(R.id.sign_in_facebook_button)
        googleButton = findViewById(R.id.sign_in_facebook_button)
        twitterButton = findViewById(R.id.sign_in_facebook_button)
    }

    var doubleBackToExitPressedOnce = false
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

    /*Button actions*/
    fun onClickFacebookButton(v: View){
        facebookButton?.performClick()
    }

    fun onClickTwitterButton(v: View){
        twitterButton?.performClick()
    }

    fun onClickGoogleButton(v: View){
        googleButton?.performClick()
    }

    fun onClickTermsAndContidions(v:View)
    {
        Toast.makeText(this, "Show terms and contidions", Toast.LENGTH_SHORT).show()
    }
}
