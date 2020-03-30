package com.gt.quedateencasa.views.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.gt.quedateencasa.R
import com.gt.quedateencasa.views.main.MainActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var layouts: Array<Int>
    private val sliderChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageSelected(position: Int) {
            if (position == layouts.size.minus(1)) {
                continueButton.text = getString(R.string.onboarding_end)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        init()
        setupListeners()
    }

    private fun init() {
        layouts = arrayOf(
            R.layout.onboarding_1,
            R.layout.onboarding_2,
            R.layout.onboarding_3,
            R.layout.onboarding_4
        )

        sliderAdapter = SliderAdapter(this, layouts)

        viewPager.apply {
            adapter = sliderAdapter
            addOnPageChangeListener(sliderChangeListener)
        }
    }

    private fun setupListeners() {
        continueButton.setOnClickListener {
            val current = viewPager.currentItem.plus(+1)
            if (current < layouts.size) {
                viewPager.currentItem = current
            } else {
                enterApp()
            }
        }
    }

    private fun enterApp() {
        // change first-launch flag value
        AppUtils(this).setFirstLaunch(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}
