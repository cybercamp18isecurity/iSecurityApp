package com.telefonica.lucferbux.isecurityapp.model

data class AlertInfo(
    val date: Number?,
    val id_external: String?,
    val id_user: String,
    val type: String,
    val status: AlertStatusType?,
    val criticity: Number?,
    val description: String?,
    val events: Any?,
    val is_deleted: String?
)

data class AlertInfoList(
    var alerts: List<AlertInfo>
)