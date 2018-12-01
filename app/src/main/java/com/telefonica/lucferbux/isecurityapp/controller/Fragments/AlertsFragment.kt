package com.telefonica.lucferbux.isecurityapp.controller.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.adapters.AlertListAdapter
import com.telefonica.lucferbux.isecurityapp.controller.Activities.AlertResolveActivity
import com.telefonica.lucferbux.isecurityapp.extension.ALERT_RESOLVE
import com.telefonica.lucferbux.isecurityapp.model.AlertInfo
import com.telefonica.lucferbux.isecurityapp.model.AlertInfoList
import com.telefonica.lucferbux.isecurityapp.model.AlertStatusType
import kotlinx.android.synthetic.main.fragment_alerts.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult
import org.jetbrains.anko.support.v4.toast


private const val ALERTS_PARAM = "alertsParams"

class AlertsFragment : Fragment() {
    var alerts: AlertInfoList? = null
    var alertsSorted: ArrayList<AlertInfo>? = null
    var alertSelected: Int? = null

    companion object {
        @JvmStatic
        fun newInstance(alerts: AlertInfoList) =
            AlertsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ALERTS_PARAM, alerts)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            alerts = it.getSerializable(ALERTS_PARAM) as AlertInfoList
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alerts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        alerts_adapter.layoutManager = LinearLayoutManager(context)
        refreshUI()
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

        alerts_adapter.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == ALERT_RESOLVE && resultCode == Activity.RESULT_OK) {
            alertSelected?.let {
                toast("Aceptado CAMBIAR ${alertSelected}")
            }


        } else {
            alertSelected.let {
                toast("No aceptado CAMBIAR ${alertSelected}")
            }
        }

    }
}
