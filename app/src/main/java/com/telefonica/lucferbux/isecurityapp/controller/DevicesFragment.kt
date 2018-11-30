package com.telefonica.lucferbux.isecurityapp.controller

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.adapters.DeviceListAdapter
import com.telefonica.lucferbux.isecurityapp.extension.DEVICE_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfo
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfoList
import kotlinx.android.synthetic.main.fragment_devices.*
import org.jetbrains.anko.support.v4.startActivity


private const val DEVICES_PARAM = "devicesParams"

class DevicesFragment : Fragment() {
    public var devices: DeviceInfoList? = null
    var devicesSorted: ArrayList<DeviceInfo>? = null

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
        refreshUI()
    }

    fun sortDevices(devicesList: DeviceInfoList): ArrayList<DeviceInfo> {
        return  ArrayList(devicesList.devices.sortedBy { it.status })
    }

    fun refreshUI() {
        devicesSorted = sortDevices(devices!!)
        val sortedDevice = devicesSorted
        val adapter = DeviceListAdapter(sortedDevice!!) {
            val device = devicesSorted!!.get(it)
            startActivity<DeviceDetailActivity>(
                DEVICE_DETAIL to device
            )
        }

        devices_adapter.adapter = adapter
    }

}
