package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class DomainInfo(
    val title: String,
    val description: String,
    val img: String,
    var statusType: StatusType,
    var lastConnection: String?
): Serializable

data class DomainInfoList(
    var domains: List<DomainInfo>
): Serializable