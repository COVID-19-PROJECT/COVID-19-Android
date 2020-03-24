package com.gt.quedateencasa.views.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.gt.quedateencasa.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
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
        sign_in_facebook_button?.performClick()
    }

    fun onClickTwitterButton(v: View){
        sign_in_twitter_button?.performClick()
    }

    fun onClickGoogleButton(v: View){
        sign_in_google_button?.performClick()
    }

    fun onClickTermsAndContidions(v:View)
    {
        Toast.makeText(this, "Show terms and contidions", Toast.LENGTH_SHORT).show()
    }

    fun onClickCreateAccount(v:View)
    {

    }
}
