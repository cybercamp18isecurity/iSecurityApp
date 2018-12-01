package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.adapters.AlertListAdapter
import com.telefonica.lucferbux.isecurityapp.extension.ALERT_DETAIL
import com.telefonica.lucferbux.isecurityapp.extension.ALERT_RESOLVE
import com.telefonica.lucferbux.isecurityapp.extension.DEVICE_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.AlertInfo
import com.telefonica.lucferbux.isecurityapp.model.AlertInfoList
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfo
import kotlinx.android.synthetic.main.activity_device_detail.*
import kotlinx.android.synthetic.main.fragment_alerts.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.support.v4.startActivityForResult

class DeviceDetailActivity : AppCompatActivity() {

    private val device: DeviceInfo by lazy {
        intent.getSerializableExtra(DEVICE_DETAIL) as DeviceInfo
    }

    private val alerts: AlertInfoList by lazy {
        intent.getSerializableExtra(ALERT_DETAIL) as AlertInfoList
    }

    var alertsSorted: ArrayList<AlertInfo>? = null
    var alertSelected: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_detail)

        device_detail_adapter.layoutManager = LinearLayoutManager(this)

        refreshUI()

        btn_close.onClick {
            closeActivity()
        }

        setDeviceInfo(device)
    }

    fun sortAlerts(alertsList: AlertInfoList): ArrayList<AlertInfo> {
        return  ArrayList(alertsList.alerts.sortedByDescending { it.date.toString() })
    }

    fun refreshUI() {
        alertsSorted = sortAlerts(alerts!!)
        val sortedAlert = alertsSorted
        val adapter = AlertListAdapter(sortedAlert!!) {
            val alert = alertsSorted!!.get(it)
            alertSelected = it
            startActivityForResult<AlertResolveActivity>(ALERT_RESOLVE)
        }

        device_detail_adapter.adapter = adapter
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d("key_press", "back button pressed");
        }
        return super.onKeyDown(keyCode, event)
    }

    fun setDeviceInfo(device: DeviceInfo) {
        device_title.text = device.hostname
        device_owner.text = device.owner
        Glide.with(this)
            .load(Uri.parse(device.avatar_url))
            .apply(RequestOptions.circleCropTransform())
            .into(device_avatar)
    }

    fun closeActivity() {
        finish()
    }

}
