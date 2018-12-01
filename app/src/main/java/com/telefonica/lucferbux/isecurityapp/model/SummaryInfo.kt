package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class SummaryInfo(
    val num_alerts: Int?,
    val num_users: Int?,
    val num_devices: Int?,
    val num_domains: Int?
): Serializable