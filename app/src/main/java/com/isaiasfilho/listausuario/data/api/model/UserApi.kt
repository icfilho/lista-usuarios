package com.isaiasfilho.listausuario.data.api.model

import com.google.gson.annotations.SerializedName
import com.isaiasfilho.listausuario.domain.user.model.User


data class UserApi (
    @SerializedName("id")
    val id: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String
){
    fun toUserLocal(): User {
        return User(
            id = this.id,
            email = this.email,
            firstName = this.firstName,
            lastName = this.lastName,
            avatar = this.avatar
        )
    }
}