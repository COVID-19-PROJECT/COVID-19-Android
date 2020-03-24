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
import com.gt.quedateencasa.databinding.ActivitySplashBinding
import com.gt.quedateencasa.views.home.HomeActivity
import com.gt.quedateencasa.views.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    var logoSplash: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var binding : ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        logoSplash = findViewById(R.id.logo_splash)
        initializeLayout()
    }

    private fun initializeLayout(){
        val handler = Handler()
        val r = Runnable {
            startActivity(HomeActivity::class.java)
        }
        handler.postDelayed(r, 1500)
    }

    private fun startActivity(welcomeClass: Class<*>) {
        val intent = Intent(applicationContext, welcomeClass)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pairs: Array<Pair<View, String>?> = arrayOfNulls(1)
            pairs[0] = Pair.create<View, String>(
                logoSplash,
                "logoTransition"
            )
            val options = ActivityOptions.makeSceneTransitionAnimation(this, *pairs)
            startActivity(intent, options.toBundle())
        } else startActivity(intent)
    }
}
