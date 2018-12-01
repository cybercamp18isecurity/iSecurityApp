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
import com.telefonica.lucferbux.isecurityapp.extension.DOMAIN_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.AlertInfo
import com.telefonica.lucferbux.isecurityapp.model.AlertInfoList
import com.telefonica.lucferbux.isecurityapp.model.DomainInfo
import kotlinx.android.synthetic.main.activity_domain_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivityForResult

class DomainDetailActivity : AppCompatActivity() {

    private val domain: DomainInfo by lazy {
        intent.getSerializableExtra(DOMAIN_DETAIL) as DomainInfo
    }

    private val alerts: AlertInfoList by lazy {
        intent.getSerializableExtra(ALERT_DETAIL) as AlertInfoList
    }

    var alertsSorted: ArrayList<AlertInfo>? = null
    var alertSelected: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_domain_detail)

        domain_detail_adapter.layoutManager = LinearLayoutManager(this)

        refreshUI()

        btn_close.onClick {
            closeActivity()
        }

        setDomainInfo(domain)
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

        domain_detail_adapter.adapter = adapter
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Log.d("key_press", "back button pressed");
        }
        return super.onKeyDown(keyCode, event)
    }

    fun setDomainInfo(domain: DomainInfo) {
        domain_title.text = domain.domain
        domain_url.text = domain.url
        Glide.with(this)
            .load(Uri.parse(domain.img_url))
            .apply(RequestOptions.circleCropTransform())
            .into(domain_avatar)
    }

    fun closeActivity() {
        finish()
    }
}
