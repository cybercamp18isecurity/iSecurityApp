package com.telefonica.lucferbux.isecurityapp.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.telefonica.lucferbux.captainamerica.recyclerview.DefaultViewHolder
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.listen
import com.telefonica.lucferbux.isecurityapp.model.AlertInfo
import com.telefonica.lucferbux.isecurityapp.model.AlertStatusType
import com.telefonica.lucferbux.isecurityapp.model.StatusType

/**
 * Adapter for the AlertList
 *
 * This class is the adapter for the recycler view of the alerts, takes an arraylist of alerts and it can short them, delete them and some sort of functions
 * Code example modified from -> https://www.raywenderlich.com/272-intermediate-recyclerview-tutorial-with-kotlin
 *
 * @property alertsList list of Alert.
 * @receiver RecyclerView.Adapter Adapter of the viewholder
 */
class AlertListAdapter (private var alertsList: ArrayList<AlertInfo>, val buttonClick: (Int) -> Unit): RecyclerView.Adapter<DefaultViewHolder>() {

    // filter to hold the result alerts
    private var filteredAlerts = ArrayList<AlertInfo>()
    private var filtering = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DefaultViewHolder(layoutInflater.inflate(R.layout.card_alert, parent, false))
    }


    override fun getItemCount(): Int {
        if(filtering) {
            return filteredAlerts.size
        }
        return alertsList.size
    }

    /**
     * clear filter array
     */
    private fun clearFilter() {
        filtering = false
        filteredAlerts.clear()
    }

    /**
     * Update the list calculating the difference between memebers
     * @param alerts New list of alerts
     */
    fun updateAlerts(alerts: ArrayList<AlertInfo>) {
        DiffUtil.calculateDiff(AlertRowDiffCallback(alerts, alertsList), false).dispatchUpdatesTo(this)
        alertsList = alerts
        clearFilter()
    }

    /**
     * Getter of alertList adapter
     * @return Arraylist of alerts
     */
    fun getListAdapter(): ArrayList<AlertInfo> {
        return alertsList
    }

    /**
     * Remove row in current adapter list
     * @param row position of the element to be removed
     */
    fun removeRow(row: Int) {
        if (filtering) {
            filteredAlerts.removeAt(row)
        } else {
            alertsList.removeAt(row)
        }
        notifyItemRemoved(row) //notifiy data changed with info about recyclerview
    }

    /**
     * Filter current alerts based of a parameter
     * @param status parameter to filter
     */
    fun filterAlerts(status: StatusType) {
        filtering = true
        val newAlerts = alertsList.filter { alert ->
            alert.status!!.equals(status)
        } as ArrayList<AlertInfo>

        DiffUtil.calculateDiff(AlertRowDiffCallback(newAlerts, alertsList), false).dispatchUpdatesTo(this)
        filteredAlerts = newAlerts
    }


/*    override fun getItemViewType(position: Int) =
        if (filtering) {
            filteredCampaign[position].type.ordinal
        } else {
            campaignList[position].type.ordinal
        }*/

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val alertRow: AlertInfo = if (filtering) {
            filteredAlerts[position]
        } else {
            alertsList[position]
        }

        alertRow.description?.let { holder.setText(R.id.alert_card_title, it) }
        alertRow.status?.let {
            holder.setText(R.id.alert_card_status, getStatus(alertRow))
            val color = getColor(it)
            holder.setColorText(R.id.alert_card_status, color)
            holder.setColorImage(R.id.alert_card_dot, color)
            val visible = it.equals(AlertStatusType.ACTIVE)
            holder.setButtonVissible(R.id.alert_card_btn, visible)
            if(visible) holder.setButtonClick(R.id.alert_card_btn, position) {
                pos -> buttonClick(position)
            }
        }
        alertRow.criticity?.let {  holder.setImage(R.id.alert_card_img, getAlertImage(it.toInt())) }
    }

    /**
     * Get current status of a alert
     * @param alert to check the status
     * @return String with the current status
     */
    fun getStatus(alert: AlertInfo): String {
        return when (alert.status!!) {
            1 -> "Activa"
            0-> "Resuelta"
            else -> "Activa"
        }
    }

    /**
     * Select image of the alert
     * @param level to check the criticity
     * @return id of the image
     */
    fun getAlertImage(level: Int): Int {
        return when (level) {
            in 1..4 -> R.drawable.ic_alert_notification_low
            in 4..7 -> R.drawable.ic_alert_notification_meium
            in 7..10 -> R.drawable.ic_alert_notification_high
            else -> R.drawable.ic_alert_notification_low
        }
    }

    /**
     * Get current color of the alert
     * @param status to check
     * @return Id of color
     */
    fun getColor(status: Int): Int {
        return when (status) {
            1 -> R.color.colorWarn
            0 -> R.color.colorGood
            else -> R.color.colorWarn
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
    class AlertRowDiffCallback(private val newRows : List<AlertInfo>, private val oldRows : List<AlertInfo>) : DiffUtil.Callback() {
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