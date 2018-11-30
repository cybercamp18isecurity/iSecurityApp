package com.telefonica.lucferbux.isecurityapp.controller

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R


private const val ALERTS_PARAM = "alertsParam"

class AlertsFragment : Fragment() {
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ALERTS_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alerts, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(alerts: String) =
            AlertsFragment().apply {
                arguments = Bundle().apply {
                    putString(ALERTS_PARAM, param1)
                }
            }
    }
}
