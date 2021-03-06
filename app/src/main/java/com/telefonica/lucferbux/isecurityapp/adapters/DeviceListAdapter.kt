package com.telefonica.lucferbux.isecurityapp.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.telefonica.lucferbux.captainamerica.recyclerview.DefaultViewHolder
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.listen
import com.telefonica.lucferbux.isecurityapp.model.DeviceInfo
import com.telefonica.lucferbux.isecurityapp.model.StatusType

/**
 * Adapter for the DeviceList
 *
 * This class is the adapter for the recycler view of the devices, takes an arraylist of devices and it can short them, delete them and some sort of functions
 * Code example modified from -> https://www.raywenderlich.com/272-intermediate-recyclerview-tutorial-with-kotlin
 *
 * @property devicesList list of Device.
 * @receiver RecyclerView.Adapter Adapter of the viewholder
 */
class DeviceListAdapter (private var devicesList: ArrayList<DeviceInfo>, val rowClick: (Int) -> Unit): RecyclerView.Adapter<DefaultViewHolder>() {

    // filter to hold the result devices
    private var filteredDevices = ArrayList<DeviceInfo>()
    private var filtering = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DefaultViewHolder(layoutInflater.inflate(R.layout.card_device, parent, false)).listen {
            pos, _ ->
                rowClick(pos)
        }
    }


    override fun getItemCount(): Int {
        if(filtering) {
            return filteredDevices.size
        }
        return devicesList.size
    }

    /**
     * clear filter array
     */
    private fun clearFilter() {
        filtering = false
        filteredDevices.clear()
    }

    /**
     * Update the list calculating the difference between memebers
     * @param devices New list of devices
     */
    fun updateDevices(devices: ArrayList<DeviceInfo>) {
        DiffUtil.calculateDiff(DeviceRowDiffCallback(devices, devicesList), false).dispatchUpdatesTo(this)
        devicesList = devices
        clearFilter()
    }

    /**
     * Getter of deviceList adapter
     * @return Arraylist of devices
     */
    fun getListAdapter(): ArrayList<DeviceInfo> {
        return devicesList
    }

    /**
     * Remove row in current adapter list
     * @param row position of the element to be removed
     */
    fun removeRow(row: Int) {
        if (filtering) {
            filteredDevices.removeAt(row)
        } else {
            devicesList.removeAt(row)
        }
        notifyItemRemoved(row) //notifiy data changed with info about recyclerview
    }

    /**
     * Filter current devices based of a parameter
     * @param status parameter to filter
     */
    fun filterDevices(status: StatusType) {
        filtering = true
        val newDevices = devicesList.filter { device ->
            device.status!!.equals(status)
        } as ArrayList<DeviceInfo>

        DiffUtil.calculateDiff(DeviceRowDiffCallback(newDevices, devicesList), false).dispatchUpdatesTo(this)
        filteredDevices = newDevices
    }


/*    override fun getItemViewType(position: Int) =
        if (filtering) {
            filteredCampaign[position].type.ordinal
        } else {
            campaignList[position].type.ordinal
        }*/

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val deviceRow: DeviceInfo = if (filtering) {
            filteredDevices[position]
        } else {
            devicesList[position]
        }

        deviceRow.hostname.let { holder.setText(R.id.device_card_title, it) }
        deviceRow.owner?.let { holder.setText(R.id.device_card_owner, it) }
        deviceRow.status?.let {
            holder.setText(R.id.device_card_status, getStatus(deviceRow))
            val color = getColor(it)
            holder.setColorText(R.id.device_card_status, color)
            holder.setColorImage(R.id.device_card_dot, color)
        }
        deviceRow.avatar_url?.let { holder.setAvatarImage(R.id.device_card_img, it) }
    }

    /**
     * Get current status of a device
     * @param device to check the status
     * @return String with the current status
     */
    fun getStatus(device: DeviceInfo): String {
        return when (device.status!!) {
            1-> "Online"
            0 -> device.timestamp?.let { "Ult. conx ${it}" } ?: "Not found"
            else -> "Online"
        }
    }

    /**
     * Get current color of the device
     * @param status to check
     * @return Id of color
     */
    fun getColor(status: Int): Int {
        return when (status) {
            0 -> R.color.colorOffline
            1-> R.color.colorGood
            else -> 1
        }
    }



    /**
     * Callback to check items in list
     *
     *
     * @property newRows list with new Rows
     * @property oldRows list with old Rows
     * @return Callback with the result list
     */
    class DeviceRowDiffCallback(private val newRows : List<DeviceInfo>, private val oldRows : List<DeviceInfo>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRow = oldRows[oldItemPosition]
            val newRow = newRows[newItemPosition]
            return oldRow.status == newRow.status
        }

        override fun getOldListSize(): Int = oldRows.size

        override fun getNewListSize(): Int = newRows.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldRow = oldRows[oldItemPosition]
            val newRow = newRows[newItemPosition]
            return oldRow == newRow
        }
    }




}