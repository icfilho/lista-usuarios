package com.isaiasfilho.listausuario.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.isaiasfilho.listausuario.domain.user.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun instance(context: Context): AppDatabase {
            synchronized(this){
                var instance: AppDatabase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "listauser.db").build()
                }

                return instance
            }

        }
    }

    abstract fun users(): UserDAO
}