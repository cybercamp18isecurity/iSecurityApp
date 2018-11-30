package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class DeviceInfo(
    val title: String,
    val owner: String,
    val img: String,
    var statusType: StatusType,
    var lastConnection: String?
): Serializable

data class DeviceInfoList(
    var devices: List<DeviceInfo>
): Serializable