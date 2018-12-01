package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class DeviceInfo(
    val timestamp: String?,
    val _id: String?,
    val hostname: String,
    val owner: String?,
    val avatar_url: String?,
    val type: String?,
    val is_deleted: Boolean?,
    var status: Int?
): Serializable

data class DeviceInfoList(
    var devices: List<DeviceInfo>
): Serializable