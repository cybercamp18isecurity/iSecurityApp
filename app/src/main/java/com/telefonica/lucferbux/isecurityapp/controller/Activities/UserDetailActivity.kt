package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.USER_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.UserInfo

import kotlinx.android.synthetic.main.activity_user_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class UserDetailActivity : AppCompatActivity() {

    private val user: UserInfo by lazy {
        intent.getSerializableExtra(USER_DETAIL) as UserInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        btn_close.onClick {
            closeActivity()
        }

        setuserInfo(user)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d("key_press", "back button pressed");
        }
        return super.onKeyDown(keyCode, event)
    }

    fun setuserInfo(user: UserInfo) {
        user_title.text = user.name
        user_device.text = user.device
        Glide.with(this)
            .load(Uri.parse(user.image_url))
            .apply(RequestOptions.circleCropTransform())
            .into(user_avatar)
    }

    fun closeActivity() {
        finish()
    }
}
