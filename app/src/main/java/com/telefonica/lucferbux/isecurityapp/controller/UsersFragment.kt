package com.telefonica.lucferbux.isecurityapp.controller

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.model.UserInfoList

private const val USERS_PARAM = "userParams"



class UsersFragment : Fragment() {
    private var users: UserInfoList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            users = it.getSerializable(USERS_PARAM) as UserInfoList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(users: UserInfoList) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USERS_PARAM, users)
                }
            }
    }
}
