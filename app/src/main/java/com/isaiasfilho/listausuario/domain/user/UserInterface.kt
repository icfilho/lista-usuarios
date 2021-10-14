package com.isaiasfilho.listausuario.domain.user

import com.isaiasfilho.listausuario.domain.user.model.User
import com.isaiasfilho.listausuario.data.api.model.UserApi

interface UserInterface{
    suspend fun listUsersRemote(): List<UserApi>
    suspend fun listUsersLocal(): List<User>
    suspend fun insert(user: User)
    suspend fun insertAll(users: List<UserApi>)
    suspend fun importUsers()
    suspend fun isUserImported(): Boolean
}

