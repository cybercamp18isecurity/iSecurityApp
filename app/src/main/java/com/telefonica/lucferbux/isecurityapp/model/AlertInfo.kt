package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class AlertInfo(
    val _id: String?,
    val id_external: String?, //hostname
    val id_user: String?,
    val type: String?,
    val status: Int?,
    val date: Int?,
    val criticity: Int?,
    val description: String?

): Serializable

data class AlertInfoList(
    var alerts: List<AlertInfo>
): Serializable