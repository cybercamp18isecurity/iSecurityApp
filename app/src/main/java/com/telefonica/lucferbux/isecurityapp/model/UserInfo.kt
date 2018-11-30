package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class UserInfo(
    val title: String,
    val description: String,
    val img: String,
    var statusType: StatusType,
    var lastConnection: String?
): Serializable

data class UserInfoList(
    var users: List<UserInfo>
): Serializable