package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class DeviceInfo(
    val timestamp: Number?,
    val hostname: String,
    val owner: String?,
    val avatar_url: String?,
    val type: String?,
    val is_deleted: Boolean?,
    var status: StatusType?
): Serializable

data class DeviceInfoList(
    var devices: List<DeviceInfo>
): Serializable