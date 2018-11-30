package com.telefonica.lucferbux.isecurityapp.controller.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.adapters.UserListAdapter
import com.telefonica.lucferbux.isecurityapp.controller.Activities.UserDetailActivity
import com.telefonica.lucferbux.isecurityapp.extension.USER_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.UserInfo
import com.telefonica.lucferbux.isecurityapp.model.UserInfoList
import kotlinx.android.synthetic.main.fragment_users.*
import org.jetbrains.anko.support.v4.startActivity

private const val USERS_PARAM = "userParams"



class UsersFragment : Fragment() {
    private var users: UserInfoList? = null
    var usersSorted: ArrayList<UserInfo>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            users = it.getSerializable(USERS_PARAM) as UserInfoList
        }
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        users_adapter.layoutManager = LinearLayoutManager(context)
        refreshUI()
    }

    fun sortUsers(usersList: UserInfoList): ArrayList<UserInfo> {
        return  ArrayList(usersList.users.sortedBy { it.status })
    }

    fun refreshUI() {
        usersSorted = sortUsers(users!!)
        val sorteduser = usersSorted
        val adapter = UserListAdapter(sorteduser!!) {
            val user = usersSorted!!.get(it)
            startActivity<UserDetailActivity>(
                USER_DETAIL to user
            )
        }

        users_adapter.adapter = adapter
    }

    
}
