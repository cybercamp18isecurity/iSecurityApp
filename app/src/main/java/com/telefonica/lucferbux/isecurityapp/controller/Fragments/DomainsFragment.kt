package com.telefonica.lucferbux.isecurityapp.controller.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.telefonica.lucferbux.isecurityapp.R
import com.telefonica.lucferbux.isecurityapp.adapters.DomainListAdapter
import com.telefonica.lucferbux.isecurityapp.controller.Activities.DomainDetailActivity
import com.telefonica.lucferbux.isecurityapp.extension.ALERT_DETAIL
import com.telefonica.lucferbux.isecurityapp.extension.DOMAIN_DETAIL
import com.telefonica.lucferbux.isecurityapp.model.AlertInfoList
import com.telefonica.lucferbux.isecurityapp.model.DomainInfo
import com.telefonica.lucferbux.isecurityapp.model.DomainInfoList
import kotlinx.android.synthetic.main.fragment_domains.*
import org.jetbrains.anko.support.v4.startActivity

private const val DOMAINS_PARAMS = "domainParams"
private const val ALERTS_PARAM = "alertsParams"

class DomainsFragment : Fragment() {
    var domains: DomainInfoList? = null
    var alerts: AlertInfoList? = null
    var domainsSorted: ArrayList<DomainInfo>? = null

    companion object {
        @JvmStatic
        fun newInstance(domains: DomainInfoList, alerts: AlertInfoList) =
            DomainsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DOMAINS_PARAMS, domains)
                    putSerializable(ALERTS_PARAM, alerts)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            domains = it.getSerializable(DOMAINS_PARAMS) as DomainInfoList
            alerts = it.getSerializable(ALERTS_PARAM) as AlertInfoList
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_domains, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        domains_adapter.layoutManager = LinearLayoutManager(context)
        refreshUI()
    }

    fun sortDomains(domainsList: DomainInfoList): ArrayList<DomainInfo> {
        return  ArrayList(domainsList.domains.sortedBy { it.status })
    }

    fun refreshUI() {
        domainsSorted = sortDomains(domains!!)
        val sortedDomain = domainsSorted
        val adapter = DomainListAdapter(sortedDomain!!) {
            val domain = domainsSorted!!.get(it)
            startActivity<DomainDetailActivity>(
                DOMAIN_DETAIL to domain,
                        ALERT_DETAIL to alerts
            )
        }

        domains_adapter.adapter = adapter
    }
}
