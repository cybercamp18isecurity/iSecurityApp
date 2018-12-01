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
        val DEVICES_TEST: DeviceInfoList = DeviceInfoList(listOf(
            DeviceInfo(1543619826, "HP Spectre X25", "Santiago Hernandez",
                "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/hpspectre.jpg?alt=media&token=2d09092c-7c2d-43ec-bd79-0596b1596b9f",
                "", false,  StatusType.ONLINE),
            DeviceInfo(1543619826, "HP Pavilion", "Becario",
                "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/hppavilion.png?alt=media&token=4c3940cb-d5d5-47d4-a863-c3c366f3b36a",
                "", false, StatusType.OFFLINE),
            DeviceInfo(1543619826, "Macbook Pro 15", "Lucas Fernández",
                "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/macbookpro.jpg?alt=media&token=6c04c132-933a-449c-94a9-945eec97fe04",
                "", false, StatusType.ONLINE),
            DeviceInfo(1543619826, "Dell XPS", "Javier Gutierrez",
                "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/dellxps.jpg?alt=media&token=d3e8f99d-4fb0-429e-aa10-b0249921f30f",
                "", false, StatusType.ONLINE)
        ))
        val USERS_TEST: UserInfoList = UserInfoList(listOf(
            UserInfo(1543619826, "Santi Hernandez", "santi6minutos@iSecurity.com", "Security Researcher",
                "Innovation", "HP Pavilion", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/santiago_foto.png?alt=media&token=3221114e-3fc2-4109-bd1a-42cd489029ef",
                StatusType.ONLINE, true, ""
            ),
            UserInfo(1543619826, "Lucas Fernández", "lucasfer@iSecurity.com", "Developer",
                "Development", "Macbook Pro 15", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/Avatar%20Background.png?alt=media&token=316d1cf5-2f34-4ec5-87d8-304e686bdf36",
                StatusType.OFFLINE, true, ""
            )
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
            AlertInfo(1543627979, "afsadf9asf89", "qwfasfasdf234", "Manipulación de red",
                AlertStatusType.ACTIVE, 7, "Manipulación de datos por estas cosas que pasan y esas cosas", "", false),
            AlertInfo(1543627379, "afsadf9asf89", "qwfasfasdf234", "Phising",
                AlertStatusType.ACTIVE, 1, "Intento de phising en la empresa que es....", "", false),
            AlertInfo(1543624979, "afsadf9asf89", "qwfasfasdf234", "Otras cosas",
                AlertStatusType.RESOLVED, 10, "Alerta de ejemplo para ver el resultado", "", false),
            AlertInfo(1543627919, "afsadf9asf89", "qwfasfasdf234", "Alerta de ejmplo abierta",
                AlertStatusType.ACTIVE, 4, "Tenemos otra alerta abierta a ver que pasa", "", false),
            AlertInfo(1543624979, "afsadf9asf89", "qwfasfasdf234", "ÚLtima notification",
                AlertStatusType.ACTIVE, 6, "Notificación abierta por esas cosas que pasan", "", false)
        ))
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
