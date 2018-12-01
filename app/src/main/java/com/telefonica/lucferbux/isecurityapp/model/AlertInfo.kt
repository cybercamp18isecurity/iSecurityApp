package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class AlertInfo(
    val date: Number?,
    val id_external: String?,
    val id_user: String?,
    val type: String,
    val status: AlertStatusType?,
    val criticity: Number?,
    val description: String?,
    val events: Any?,
    val is_deleted: Boolean?
): Serializable

data class AlertInfoList(
    var alerts: List<AlertInfo>
): Serializable