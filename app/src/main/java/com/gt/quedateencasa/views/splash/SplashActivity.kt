package com.gt.quedateencasa.views.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.gt.quedateencasa.R
import com.gt.quedateencasa.views.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initializeLayout()
    }

    private fun initializeLayout(){
        val handler = Handler()
        val r = Runnable {
            startActivity(WelcomeActivity::class.java)
        }
        handler.postDelayed(r, 1500)
    }

    private fun startActivity(welcomeClass: Class<*>) {
        val intent = Intent(applicationContext, welcomeClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pairs: Array<Pair<View, String>?> = arrayOfNulls(1)
            pairs[0] = Pair.create<View, String>(
                logo_splash,
                "logoTransition"
            )
            val options = ActivityOptions.makeSceneTransitionAnimation(this, *pairs)
            startActivity(intent, options.toBundle())
        } else startActivity(intent)
    }
}
