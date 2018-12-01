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
            DomainInfo(1543627979, "https://pigram.luca-d3.com", "17.34.34.56", "Pigram",
                "Pigram es un servicio que permite conectarse a la red a través de tecnología GSM, está pensado en situaciones de emergencia",
                "ElevenPaths", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/Pigram.png?alt=media&token=b06e8d95-7e82-4c6e-a5b0-173612f9c88b",
                "", StatusType.ONLINE, false, "", "", ""),
            DomainInfo(1543637979, "https://cardreader.e-paths.com", "17.20.10.56", "Card Reader",
                "Card Reader es una aplicación que extrae toda la información posible de una tarjeta de contacto",
                "Ideas Locas", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/CardReader.png?alt=media&token=ea989413-42ae-4b15-946e-e67ee8af91a1",
                "", StatusType.OFFLINE, false, "", "", "")
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
        waitAndDo(100){
            loadData()
            requestData()
        }
    }

    fun loadData() {
        //deviceList = DEVICES_TEST
        //usersList = USERS_TEST
        domainList = DOMAIN_TEST
        //alertList = ALERT_TEST
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
                summaryInfo = response
                return@flatMap retriever.service.retrieveAlerts() }
            .flatMap { response ->
                alertList = response
                return@flatMap  retriever.service.retrieveDevice()
            }
            .flatMap { response ->
                deviceList = response
                return@flatMap retriever.service.retreiveUsers()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { response: UserInfoList ->
                    usersList = response
                    startApp()
                },
                { err -> Log.v("call_log", err.localizedMessage) }
            )
    }


}
