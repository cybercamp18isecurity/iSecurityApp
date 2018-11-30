package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class DomainInfo(
    val timestamp: Number?,
    val url: String,
    val ip: String?,
    val domain: String?,
    val description: String?,
    val owner: String?,
    val img_url: String,
    val old_img_url: String,
    val status: StatusType,
    val isDeleted: Boolean?,
    val htmlcode_url: String?,
    val old_htmlcode_url: String?,
    val events: Any?
): Serializable

data class DomainInfoList(
    var domains: List<DomainInfo>
): Serializable