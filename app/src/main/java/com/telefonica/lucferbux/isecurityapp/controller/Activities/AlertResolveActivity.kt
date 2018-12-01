package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.telefonica.lucferbux.isecurityapp.R
import kotlinx.android.synthetic.main.activity_alert_resolve.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class AlertResolveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert_resolve)

        btn_decline.onClick {
            setResult(Activity.RESULT_CANCELED)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        btn_accept.onClick {
            setResult(Activity.RESULT_OK)
            finish()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
