package com.telefonica.lucferbux.isecurityapp.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.adapters.DeviceListAdapter
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfo
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfoList
import kotlinx.android.synthetic.main.fragment_devices.*


private const val DEVICES_PARAM = "devicesParams"

class DevicesFragment : Fragment() {
    private var devices: DeviceInfoList? = null

    companion object {
        @JvmStatic
        fun newInstance(devices: DeviceInfoList) =
            DevicesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DEVICES_PARAM, devices)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            devices = it.getSerializable(DEVICES_PARAM) as DeviceInfoList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        devices_adapter.layoutManager = LinearLayoutManager(context)

        val sortedDevices = sortDevices(devices!!)
        val adapter = DeviceListAdapter(sortedDevices)

        devices_adapter.adapter = adapter
    }

    fun sortDevices(devicesList: DeviceInfoList): ArrayList<DeviceInfo> {
        return  ArrayList(devicesList.devices.sortedBy { it.statusType })
    }

}
