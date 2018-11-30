package com.telefonica.lucferbux.isecurityapp.controller

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.createNavigation
import com.telefonica.lucferbux.isecurityapp.extension.setNavigationLinks
import com.telefonica.lucferbux.isecurityapp.extension.setPushNotification
import com.telefonica.lucferbux.isecurityapp.model.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        //mockup for fragments
        val DEVICES_TEST: DeviceInfoList = DeviceInfoList(listOf(
            DeviceInfo("HP Spectre X25", "Santiago Hernandez", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/hpspectre.jpg?alt=media&token=2d09092c-7c2d-43ec-bd79-0596b1596b9f", StatusType.ONLINE, "30/11 16:00"),
            DeviceInfo("HP Pavilion", "Becario", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/hppavilion.png?alt=media&token=4c3940cb-d5d5-47d4-a863-c3c366f3b36a", StatusType.OFFLINE, "30/11 20:00"),
            DeviceInfo("Macbook Pro 15", "Lucas Fernández", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/macbookpro.jpg?alt=media&token=6c04c132-933a-449c-94a9-945eec97fe04", StatusType.ONLINE, "30/11 21:00"),
            DeviceInfo("Dell XPS", "Javier Gutierrez", "https://firebasestorage.googleapis.com/v0/b/isecurity-176d0.appspot.com/o/dellxps.jpg?alt=media&token=d3e8f99d-4fb0-429e-aa10-b0249921f30f", StatusType.ONLINE, "30/11 19:00")
        ))
        val USERS_TEST: UserInfoList = UserInfoList(listOf())
        val DOMAIN_TEST: DomainInfoList = DomainInfoList(listOf())
    }

    var devicesFragment: DevicesFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation(navigation_bar)
        setNavigationLinks(navigation_bar, this)
        setPushNotification()
        if(savedInstanceState == null) {
            devicesFragment = DevicesFragment.newInstance(DEVICES_TEST)
            supportFragmentManager.beginTransaction().add(R.id.navigation_fragment, devicesFragment).commit()
        }
    }

}
