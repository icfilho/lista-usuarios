package com.isaiasfilho.listausuario.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaiasfilho.listausuario.domain.user.model.User


@Dao
abstract class UserDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(user: User)

    @Query("select * from User")
    abstract suspend fun getAll(): List<User>
}