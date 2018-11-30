package com.telefonica.lucferbux.isecurityapp.extension

import android.content.Context
import android.support.v4.app.Fragment
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceNavigationView
import com.luseen.spacenavigation.SpaceOnClickListener
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.controller.Activities.DashboardActivity
import com.telefonica.lucferbux.isecurityapp.controller.Activities.MainActivity
import com.telefonica.lucferbux.isecurityapp.controller.Fragments.AlertsFragment
import com.telefonica.lucferbux.isecurityapp.controller.Fragments.DevicesFragment
import com.telefonica.lucferbux.isecurityapp.controller.Fragments.DomainsFragment
import com.telefonica.lucferbux.isecurityapp.controller.Fragments.UsersFragment
import com.telefonica.lucferbux.isecurityapp.model.NavigationFragment
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
                    fragmentStatus = NavigationFragment.DEVICES
                    DevicesFragment.newInstance(deviceList!!)
                }

                1 -> {
                    fragmentStatus = NavigationFragment.USERS
                    UsersFragment.newInstance(usersList!!)
                }

                2 -> {
                    fragmentStatus = NavigationFragment.DOMAINS
                    DomainsFragment.newInstance(domainList!!)
                }

                3 -> {
                    fragmentStatus = NavigationFragment.ALERTS
                    AlertsFragment.newInstance("alerts")
                }

                else -> {
                    fragmentStatus = NavigationFragment.DEVICES
                    DevicesFragment.newInstance(deviceList!!)
                }
            }

            supportFragmentManager.beginTransaction().replace(R.id.navigation_fragment, fragment).commit()
        }

        override fun onCentreButtonClick() {
                startActivity<DashboardActivity>()
        }
    })
}