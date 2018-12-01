package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.messaging.RemoteMessage
import com.pusher.pushnotifications.PushNotificationReceivedListener
import com.pusher.pushnotifications.PushNotifications
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.controller.Fragments.DevicesFragment
import com.telefonica.lucferbux.isecurityapp.extension.createNavigation
import com.telefonica.lucferbux.isecurityapp.extension.setNavigationLinks
import com.telefonica.lucferbux.isecurityapp.extension.setPushNotification
import com.telefonica.lucferbux.isecurityapp.extension.toast
import com.telefonica.lucferbux.isecurityapp.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        //mockup for fragments
        val DEVICES_TEST: DeviceInfoList = DeviceInfoList(listOf())
        val USERS_TEST: UserInfoList = UserInfoList(listOf())
        val DOMAIN_TEST: DomainInfoList = DomainInfoList(listOf())
        val ALERT_TEST: AlertInfoList = AlertInfoList(listOf())
    }

    var fragmentStatus: NavigationFragment? = null

    var deviceList: DeviceInfoList? = null
    var usersList: UserInfoList? = null
    var domainList: DomainInfoList? = null
    var alertList: AlertInfoList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation(navigation_bar)
        setNavigationLinks(navigation_bar, this)
        setPushNotification()
        loadData()
        if(savedInstanceState == null) {
            fragmentStatus = NavigationFragment.DEVICES
            val devicesFragment = DevicesFragment.newInstance(deviceList!!)
            supportFragmentManager.beginTransaction().add(R.id.navigation_fragment, devicesFragment).commit()
        }
    }

    override fun onResume() {
        super.onResume()
        PushNotifications.setOnMessageReceivedListenerForVisibleActivity(this, object :
            PushNotificationReceivedListener {
            override fun onMessageReceived(remoteMessage: RemoteMessage) {
                runOnUiThread {
                    updateNotification()
                }
            }
        })
    }

    fun loadData() {
        deviceList = DEVICES_TEST
        usersList = USERS_TEST
        domainList = DOMAIN_TEST
        alertList = ALERT_TEST
    }

    fun updateNotification() {
        when(fragmentStatus)  {
            NavigationFragment.DEVICES -> {
                val deviceFrag = supportFragmentManager.findFragmentById(R.id.navigation_fragment) as DevicesFragment
                deviceFrag.devices = deviceList
                deviceFrag.refreshUI()
                toast("Dispositivo desconectado")
            }

            NavigationFragment.USERS -> {
                toast("Usuario desconectado")
            }

            NavigationFragment.ALERTS -> {
                toast("Nueva Alerta")
            }

            NavigationFragment.DOMAINS -> {
                toast("Nuevo dominio")
            }

        }

    }

}
