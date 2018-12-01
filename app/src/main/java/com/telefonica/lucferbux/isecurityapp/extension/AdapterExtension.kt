package com.telefonica.lucferbux.isecurityapp.extension

import android.support.v7.widget.RecyclerView


/**
 * Add click handler to activity
 * from -> https://stackoverflow.com/questions/29424944/recyclerview-itemclicklistener-in-kotlin
 */
fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}
