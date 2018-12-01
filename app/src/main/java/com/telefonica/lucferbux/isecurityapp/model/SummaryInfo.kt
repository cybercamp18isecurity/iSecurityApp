package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class SummaryInfo(
    val alertCount: Number,
    val userCount: Number,
    val deviceCount: Number,
    val domainCount: Number
): Serializable