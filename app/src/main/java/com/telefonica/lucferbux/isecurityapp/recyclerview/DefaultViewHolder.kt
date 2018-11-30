package com.telefonica.lucferbux.captainamerica.recyclerview

import android.net.Uri
import android.support.annotation.IdRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewMap: MutableMap<Int, View> = HashMap()

    init {
        findViewItems(itemView)
    }

    fun setText(@IdRes id: Int, text: String) {
        val view = (viewMap[id] ?: throw IllegalArgumentException("View for $id not found")) as? TextView ?: throw IllegalArgumentException("View for $id is not a TextView")
        view.text = text
    }


    fun setColorText(@IdRes id: Int, color: Int) {
        val view = (viewMap[id] ?: throw IllegalArgumentException("View for $id not found")) as? TextView ?: throw IllegalArgumentException("View for $id is not a TextView")
        view.setTextColor(ContextCompat.getColor(itemView.context, color))
    }

    fun setColorImage(@IdRes id: Int, color: Int) {
        val view = (viewMap[id] ?: throw IllegalArgumentException("View for $id not found")) as? ImageView ?: throw IllegalArgumentException("View for $id is not a View")
        view.setColorFilter(ContextCompat.getColor(itemView.context, color))
    }

    fun setAvatarImage(@IdRes id: Int, url: String) {
        val view = (viewMap[id] ?: throw IllegalArgumentException("View for $id not found")) as? ImageView ?: throw IllegalArgumentException("View for $id is not a View")
        Glide.with(itemView.context)
            .load(Uri.parse(url))
            .into(view)
    }


    private fun findViewItems(itemView: View) {
        addToMap(itemView)
        if (itemView is ViewGroup) {
            val childCount = itemView.childCount
            (0 until childCount)
                    .map { itemView.getChildAt(it) }
                    .forEach { findViewItems(it) }
        }
    }

    private fun addToMap(itemView: View) {
        if (itemView.id == View.NO_ID) {
            itemView.id = View.generateViewId()
        }
        viewMap.put(itemView.id, itemView)
    }
}
