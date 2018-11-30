package com.telefonica.lucferbux.isecurityapp.controller.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.model.DomainInfoList

private const val DOMAINS_PARAMS = "domainParams"

class DomainsFragment : Fragment() {
    private var domains: DomainInfoList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            domains = it.getSerializable(DOMAINS_PARAMS) as DomainInfoList
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_domains, container, false)
    }


    companion object {

        @JvmStatic
        fun newInstance(domains: DomainInfoList) =
            DomainsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DOMAINS_PARAMS, domains)
                }
            }
    }
}
