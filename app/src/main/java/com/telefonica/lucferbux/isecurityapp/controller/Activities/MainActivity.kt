package com.telefonica.lucferbux.isecurityapp.controller.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.messaging.RemoteMessage
import com.pusher.pushnotifications.PushNotificationReceivedListener
import com.pusher.pushnotifications.PushNotifications
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.controller.Activities.SplashActivity.Companion.ALERT_TEST
import com.telefonica.lucferbux.isecurityapp.controller.Activities.SplashActivity.Companion.DEVICES_TEST
import com.telefonica.lucferbux.isecurityapp.controller.Activities.SplashActivity.Companion.DOMAIN_TEST
import com.telefonica.lucferbux.isecurityapp.controller.Activities.SplashActivity.Companion.USERS_TEST
import com.telefonica.lucferbux.isecurityapp.controller.Fragments.DevicesFragment
import com.telefonica.lucferbux.isecurityapp.extension.*
import com.telefonica.lucferbux.isecurityapp.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var fragmentStatus: NavigationFragment? = null

    val deviceList: DeviceInfoList by lazy {
        intent.getSerializableExtra(DEVICE_DETAIL) as DeviceInfoList
    }

    val usersList: UserInfoList by lazy {
        intent.getSerializableExtra(USER_DETAIL) as UserInfoList
    }

    val domainList: DomainInfoList by lazy {
        intent.getSerializableExtra(DOMAIN_DETAIL) as DomainInfoList
    }

    val alertList: AlertInfoList by lazy {
        intent.getSerializableExtra(ALERT_DETAIL) as AlertInfoList
    }

    val summaryInfo: SummaryInfo by lazy {
        intent.getSerializableExtra(SUMMARY_DETAIL) as SummaryInfo
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation(navigation_bar)
        setNavigationLinks(navigation_bar, this)
        setPushNotification()
        if(savedInstanceState == null) {
            fragmentStatus = NavigationFragment.DEVICES
            val devicesFragment = DevicesFragment.newInstance(deviceList!!, alertList!!)
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
