package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.telefonica.lucferbux.isecurityapp.R
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startApp()
    }


    fun startApp() {
        startActivity<MainActivity>(
        )
        finish()
    }
}
