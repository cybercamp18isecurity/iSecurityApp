package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class SummaryInfo(
    val num_alerts: Number,
    val num_users: Number,
    val num_device: Number,
    val num_domains: Number
): Serializable