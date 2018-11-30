package com.telefonica.lucferbux.isecurityapp.controller

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.DEVICE_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfo
import kotlinx.android.synthetic.main.activity_device_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DeviceDetailActivity : AppCompatActivity() {

    private val device: DeviceInfo by lazy {
        intent.getSerializableExtra(DEVICE_DETAIL) as DeviceInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_detail)

        btn_close.onClick {
            closeActivity()
        }

        setDeviceInfo(device)
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
