package com.telefonica.lucferbux.isecurityapp.model

import java.io.Serializable

data class UserInfo(
    val _id: String?,
    val timestamp: String?,
    val name: String,
    val email: String?,
    val position: String?,
    val department: String?,
    val device: String?,
    val image_url: String?,
    val status: Int?,
    val is_deleted: Boolean?
): Serializable

data class UserInfoList(
    var users: List<UserInfo>
): Serializable