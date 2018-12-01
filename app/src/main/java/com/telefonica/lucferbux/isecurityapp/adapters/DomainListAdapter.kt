package com.telefonica.lucferbux.isecurityapp.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.telefonica.lucferbux.captainamerica.recyclerview.DefaultViewHolder
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.listen
import com.telefonica.lucferbux.isecurityapp.model.StatusType
import com.telefonica.lucferbux.isecurityapp.model.DomainInfo

/**
 * Adapter for the DomainList
 *
 * This class is the adapter for the recycler view of the domains, takes an arraylist of domains and it can short them, delete them and some sort of functions
 * Code example modified from -> https://www.raywenderlich.com/272-intermediate-recyclerview-tutorial-with-kotlin
 *
 * @property domainsList list of domain.
 * @receiver RecyclerView.Adapter Adapter of the viewholder
 */
class DomainListAdapter (private var domainsList: ArrayList<DomainInfo>, val rowClick: (Int) -> Unit): RecyclerView.Adapter<DefaultViewHolder>() {

    // filter to hold the result domains
    private var filteredDomains = ArrayList<DomainInfo>()
    private var filtering = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DefaultViewHolder(layoutInflater.inflate(R.layout.card_domain, parent, false)).listen {
                pos, _ ->
            rowClick(pos)
        }
    }


    override fun getItemCount(): Int {
        if(filtering) {
            return filteredDomains.size
        }
        return domainsList.size
    }

    /**
     * clear filter array
     */
    private fun clearFilter() {
        filtering = false
        filteredDomains.clear()
    }

    /**
     * Update the list calculating the difference between memebers
     * @param domains New list of domains
     */
    fun updatedomains(domains: ArrayList<DomainInfo>) {
        DiffUtil.calculateDiff(domainRowDiffCallback(domains, domainsList), false).dispatchUpdatesTo(this)
        domainsList = domains
        clearFilter()
    }

    /**
     * Getter of domainList adapter
     * @return Arraylist of domains
     */
    fun getListAdapter(): ArrayList<DomainInfo> {
        return domainsList
    }

    /**
     * Remove row in current adapter list
     * @param row position of the element to be removed
     */
    fun removeRow(row: Int) {
        if (filtering) {
            filteredDomains.removeAt(row)
        } else {
            domainsList.removeAt(row)
        }
        notifyItemRemoved(row) //notifiy data changed with info about recyclerview
    }

    /**
     * Filter current domains based of a parameter
     * @param status parameter to filter
     */
    fun filterdomains(status: StatusType) {
        filtering = true
        val newdomains = domainsList.filter { domain ->
            domain.status!!.equals(status)
        } as ArrayList<DomainInfo>

        DiffUtil.calculateDiff(domainRowDiffCallback(newdomains, domainsList), false).dispatchUpdatesTo(this)
        filteredDomains = newdomains
    }


/*    override fun getItemViewType(position: Int) =
        if (filtering) {
            filteredCampaign[position].type.ordinal
        } else {
            campaignList[position].type.ordinal
        }*/

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val domainRow: DomainInfo = if (filtering) {
            filteredDomains[position]
        } else {
            domainsList[position]
        }

        domainRow.domain?.let { holder.setText(R.id.domain_card_title, it) }
        domainRow.description?.let { holder.setText(R.id.domain_card_description, it) }
        domainRow.status?.let {
            holder.setText(R.id.domain_card_status, getStatus(domainRow))
            val color = getColor(it)
            holder.setColorText(R.id.domain_card_status, color)
            holder.setColorImage(R.id.domain_card_dot, color)
        }
        domainRow.img_url?.let { holder.setAvatarImage(R.id.domain_card_img, it) }
    }

    /**
     * Get current status of a domain
     * @param domain to check the status
     * @return String with the current status
     */
    fun getStatus(domain: DomainInfo): String {
        return when (domain.status!!) {
            StatusType.ONLINE -> "Activo"
            StatusType.OFFLINE -> "No disponible"
        }
    }

    /**
     * Get current color of the domain
     * @param status to check
     * @return Id of color
     */
    fun getColor(status: StatusType): Int {
        return when (status) {
            StatusType.OFFLINE -> R.color.colorWarn
            StatusType.ONLINE -> R.color.colorGood
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
    class domainRowDiffCallback(private val newRows : List<DomainInfo>, private val oldRows : List<DomainInfo>) : DiffUtil.Callback() {
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