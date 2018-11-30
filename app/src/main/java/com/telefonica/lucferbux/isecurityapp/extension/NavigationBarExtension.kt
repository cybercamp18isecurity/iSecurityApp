package com.telefonica.lucferbux.isecurityapp.extension

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.controller.*
import com.telefonica.lucferbux.isecurityapp.controller.MainActivity.Companion.DEVICES_TEST
import com.telefonica.lucferbux.isecurityapp.controller.MainActivity.Companion.DOMAIN_TEST
import com.telefonica.lucferbux.isecurityapp.controller.MainActivity.Companion.USERS_TEST
import org.jetbrains.anko.startActivity


fun MainActivity.createNavigation(navigationBar: SpaceNavigationView) {
    navigationBar.addSpaceItem(SpaceItem("Devices", R.drawable.ic_navigation_devices))
    navigationBar.addSpaceItem(SpaceItem("Users", R.drawable.ic_navigation_users))
    navigationBar.addSpaceItem(SpaceItem("Domains", R.drawable.ic_navigation_domains))
    navigationBar.addSpaceItem(SpaceItem("Alerts", R.drawable.ic_navigation_alerts))
    navigationBar.showIconOnly()
}

fun MainActivity.setNavigationLinks(navigationBar: SpaceNavigationView, context: Context) {
    navigationBar.setSpaceOnClickListener(object: SpaceOnClickListener {

        override fun onItemReselected(itemIndex: Int, itemName: String?) {

        }

        override fun onItemClick(itemIndex: Int, itemName: String?) {
            val fragment: Fragment = when (itemIndex) {
                0 -> {
                    DevicesFragment.newInstance(DEVICES_TEST)
                }

                1 -> {
                    UsersFragment.newInstance(USERS_TEST)
                }

                2 -> {
                    DomainsFragment.newInstance(DOMAIN_TEST)
                }

                3 -> {
                    AlertsFragment.newInstance("alerts")
                }

                else -> {
                    DevicesFragment.newInstance(DEVICES_TEST)
                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.navigation_fragment, fragment).commit()
        }

        override fun onCentreButtonClick() {
                startActivity<DashboardActivity>()
        }
    })
}