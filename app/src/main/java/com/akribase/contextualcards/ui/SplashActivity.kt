package com.akribase.contextualcards.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.ui.CardComponent
import com.akribase.contextualcards.R
import com.akribase.contextualcards.databinding.ActivityMainBinding
import com.akribase.contextualcards.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT = 4500L
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT
        )
    }

//    override fun onResume() {
//        super.onResume()
//        binding.ml.transitionToEnd()
//    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

}