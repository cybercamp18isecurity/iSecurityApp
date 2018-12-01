package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.animation.DecelerateInterpolator
import com.telefonica.lucferbux.isecurityapp.R

import com.telefonica.lucferbux.isecurityapp.extension.SUMMARY_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.SummaryInfo
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DashboardActivity : AppCompatActivity() {

    private val summary: SummaryInfo by lazy {
        intent.getSerializableExtra(SUMMARY_DETAIL) as SummaryInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        refreshUI(summary)
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


    fun increaseProgresBar(progress: Int, max: Int) {
        val totalProgress =  progress / max.toDouble() * 100

        val animator: ObjectAnimator = ObjectAnimator.ofInt(issue_progress, "progress", issue_progress.progress, totalProgress.toInt())
        animator.duration = 500
        animator.interpolator = DecelerateInterpolator()
        animator.start()
        total_issues_lbl.text = progress.toString()
    }

    fun refreshUI(summary: SummaryInfo) {
        increaseProgresBar(summary.num_alerts.toInt(), 100)
        devices_dasboard_cnt.text = summary.num_device.toString()
        domains_dasboard_cnt.text = summary.num_domains.toString()
        users_dasboard_cnt.text = summary.num_users.toString()
    }


}
