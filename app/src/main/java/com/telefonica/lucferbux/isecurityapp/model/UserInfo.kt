package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class UserInfo(
    val _id: String?,
    val timestamp: Number?,
    val name: String,
    val email: String?,
    val position: String?,
    val department: String?,
    val device: String?,
    val image_url: String?,
    val status: StatusType?,
    val is_deleted: Boolean?,
    val event: Any?
): Serializable

data class UserInfoList(
    var users: List<UserInfo>
): Serializable