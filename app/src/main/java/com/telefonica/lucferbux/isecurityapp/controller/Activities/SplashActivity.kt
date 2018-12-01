package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.*
import com.telefonica.lucferbux.isecurityapp.model.*
import com.telefonica.lucferbux.isecurityapp.networking.DataRetriever
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        //mockup for fragments
        val DEVICES_TEST: DeviceInfoList = DeviceInfoList(listOf(

        ))
        val USERS_TEST: UserInfoList = UserInfoList(listOf(

        ))
        val DOMAIN_TEST: DomainInfoList = DomainInfoList(listOf(

        ))
        val ALERT_TEST: AlertInfoList = AlertInfoList(listOf(

        ))

        //val SUMMARY_INFO: SummaryInfo = SummaryInfo()

    }

    var deviceList: DeviceInfoList? = null
    var usersList: UserInfoList? = null
    var domainList: DomainInfoList? = null
    var alertList: AlertInfoList? = null
    var summaryInfo: SummaryInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //loadData()
        requestData()
    }

    fun loadData() {
        deviceList = DEVICES_TEST
        usersList = USERS_TEST
        domainList = DOMAIN_TEST
        alertList = ALERT_TEST
        //summaryInfo = SUMMARY_INFO
    }

    fun startApp() {
        startActivity<MainActivity>(
            DEVICE_DETAIL to deviceList,
            USER_DETAIL to usersList,
            DOMAIN_DETAIL to domainList,
            ALERT_DETAIL to alertList,
            SUMMARY_DETAIL to summaryInfo
        )
        finish()
    }


    fun requestData() {
        val retriever = DataRetriever()
        val userRequest = retriever.service.retrieveSummary()
        userRequest
            .flatMap { response ->
                summaryInfo = response as SummaryInfo
                return@flatMap retriever.service.retrieveAlerts() }
            .flatMap { response ->
                alertList = response as AlertInfoList
                return@flatMap  retriever.service.retrieveDevice()
            }
            .flatMap { response ->
                deviceList = response as DeviceInfoList
                return@flatMap retriever.service.retreiveUsers()
            }
            .flatMap { response ->
                usersList = response as UserInfoList
                return@flatMap retriever.service.retrieveDomains()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { response: DomainInfoList ->
                    domainList = response
                    startApp()
                },
                { err -> Log.v("call_log", err.localizedMessage) }
            )
    }


}
