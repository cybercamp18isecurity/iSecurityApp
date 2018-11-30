package com.telefonica.lucferbux.isecurityapp.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.telefonica.lucferbux.captainamerica.recyclerview.DefaultViewHolder
import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.extension.listen
import com.telefonica.lucferbux.isecurityapp.model.UserInfo
import com.telefonica.lucferbux.isecurityapp.model.StatusType

/**
 * Adapter for the UserList
 *
 * This class is the adapter for the recycler view of the users, takes an arraylist of users and it can short them, delete them and some sort of functions
 * Code example modified from -> https://www.raywenderlich.com/272-intermediate-recyclerview-tutorial-with-kotlin
 *
 * @property usersList list of user.
 * @receiver RecyclerView.Adapter Adapter of the viewholder
 */
class UserListAdapter (private var usersList: ArrayList<UserInfo>, val rowClick: (Int) -> Unit): RecyclerView.Adapter<DefaultViewHolder>() {

    // filter to hold the result users
    private var filteredUsers = ArrayList<UserInfo>()
    private var filtering = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DefaultViewHolder(layoutInflater.inflate(R.layout.card_user, parent, false)).listen {
                pos, type ->
            rowClick(pos)
        }
    }


    override fun getItemCount(): Int {
        if(filtering) {
            return filteredUsers.size
        }
        return usersList.size
    }

    /**
     * clear filter array
     */
    private fun clearFilter() {
        filtering = false
        filteredUsers.clear()
    }

    /**
     * Update the list calculating the difference between memebers
     * @param users New list of users
     */
    fun updateusers(users: ArrayList<UserInfo>) {
        DiffUtil.calculateDiff(userRowDiffCallback(users, usersList), false).dispatchUpdatesTo(this)
        usersList = users
        clearFilter()
    }

    /**
     * Getter of userList adapter
     * @return Arraylist of users
     */
    fun getListAdapter(): ArrayList<UserInfo> {
        return usersList
    }

    /**
     * Remove row in current adapter list
     * @param row position of the element to be removed
     */
    fun removeRow(row: Int) {
        if (filtering) {
            filteredUsers.removeAt(row)
        } else {
            usersList.removeAt(row)
        }
        notifyItemRemoved(row) //notifiy data changed with info about recyclerview
    }

    /**
     * Filter current users based of a parameter
     * @param status parameter to filter
     */
    fun filterusers(status: StatusType) {
        filtering = true
        val newusers = usersList.filter { user ->
            user.status!!.equals(status)
        } as ArrayList<UserInfo>

        DiffUtil.calculateDiff(userRowDiffCallback(newusers, usersList), false).dispatchUpdatesTo(this)
        filteredUsers = newusers
    }


/*    override fun getItemViewType(position: Int) =
        if (filtering) {
            filteredCampaign[position].type.ordinal
        } else {
            campaignList[position].type.ordinal
        }*/

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        val userRow: UserInfo = if (filtering) {
            filteredUsers[position]
        } else {
            usersList[position]
        }

        userRow.name.let { holder.setText(R.id.user_card_title, it) }
        userRow.position?.let { holder.setText(R.id.user_card_job, it) }
        userRow.status?.let {
            holder.setText(R.id.user_card_status, getStatus(userRow))
            val color = getColor(it)
            holder.setColorText(R.id.user_card_status, color)
            holder.setColorImage(R.id.user_card_dot, color)
        }
        userRow.image_url?.let { holder.setAvatarImage(R.id.user_card_img, it) }
        userRow.department?.let { holder.setText(R.id.user_card_department, it) }
    }

    /**
     * Get current status of a user
     * @param user to check the status
     * @return String with the current status
     */
    fun getStatus(user: UserInfo): String {
        return when (user.status!!) {
            StatusType.ONLINE -> "Activo"
            StatusType.OFFLINE -> user.timestamp?.let { "Ult. conx ${it}" } ?: "Not found"
        }
    }

    /**
     * Get current color of the user
     * @param status to check
     * @return Id of color
     */
    fun getColor(status: StatusType): Int {
        return when (status) {
            StatusType.OFFLINE -> R.color.colorOffline
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
    class userRowDiffCallback(private val newRows : List<UserInfo>, private val oldRows : List<UserInfo>) : DiffUtil.Callback() {
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