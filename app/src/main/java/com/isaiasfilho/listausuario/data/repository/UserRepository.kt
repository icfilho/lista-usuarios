package com.isaiasfilho.listausuario.data.repository
import com.isaiasfilho.listausuario.data.db.AppDatabase
import com.isaiasfilho.listausuario.domain.user.model.User
import com.isaiasfilho.listausuario.data.api.model.UserApi
import com.isaiasfilho.listausuario.domain.user.UserInterface
import com.isaiasfilho.listausuario.network.ApiService


class UserRepository (
    private val databaseSource: AppDatabase,
    private val remoteSource: ApiService
) : UserInterface {

    override suspend fun listUsersRemote(): List<UserApi> {
        return remoteSource.retrofitService.getUsers().data
    }

    override suspend fun listUsersLocal(): List<User> {
        return databaseSource.users().getAll()
    }

    override suspend fun insert(user: User){
        databaseSource.users().insert(user = user)
    }

    override suspend fun insertAll(users: List<UserApi>) {

        users.forEach {
            val userLocal = it.toUserLocal()
            insert(userLocal)
        }
    }

    override suspend fun importUsers(){
        val usersRemote: List<UserApi> = listUsersRemote()
        insertAll(usersRemote)
    }

    override suspend fun  isUserImported(): Boolean {
        val users = listUsersLocal()
        if (users.isNotEmpty()){
            return true
        }
        return false
    }
}