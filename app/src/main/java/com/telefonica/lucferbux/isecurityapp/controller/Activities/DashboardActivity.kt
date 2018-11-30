package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.telefonica.lucferbux.isecurityapp.R
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        btn_close.onClick {
            closeActivity()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d("key_press", "back button pressed");
        }
        return super.onKeyDown(keyCode, event)
    }

    fun closeActivity() {
        finish()
    }


}
