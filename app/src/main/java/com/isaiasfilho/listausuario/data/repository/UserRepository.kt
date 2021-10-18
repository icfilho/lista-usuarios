package com.isaiasfilho.listausuario.data.repository
import com.isaiasfilho.listausuario.data.api.ApiInterface
import com.isaiasfilho.listausuario.data.db.AppDatabase
import com.isaiasfilho.listausuario.domain.user.model.User
import com.isaiasfilho.listausuario.data.api.model.UserApi
import com.isaiasfilho.listausuario.data.db.UserDAO
import com.isaiasfilho.listausuario.domain.user.IUserRepository
import com.isaiasfilho.listausuario.network.ApiService


class UserRepository (
    private val databaseSource: UserDAO,
    private val remoteSource: ApiInterface
) : IUserRepository {

    override suspend fun listUsersRemote(): List<UserApi> {
        return remoteSource.getUsers().data
    }

    override suspend fun listUsersLocal(): List<User> {
        return databaseSource.getAll()
    }

    override suspend fun insert(user: User){
        databaseSource.insert(user = user)
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
        return users.isNotEmpty()
    }
}