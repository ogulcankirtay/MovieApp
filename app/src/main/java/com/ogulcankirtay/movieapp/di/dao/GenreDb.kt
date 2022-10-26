package com.ogulcankirtay.movieapp.di.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GenreData::class], version = 1, exportSchema =false )
abstract class GenreDb : RoomDatabase(){

    abstract fun getDao(): GenreDao
    companion object{
        private var dbInstance: GenreDb?=null

        fun getAppdb(context: Context) :GenreDb{
           if(dbInstance==null)
           {
               dbInstance=Room.databaseBuilder<GenreDb>(context.applicationContext,GenreDb::class.java,"genre_database").allowMainThreadQueries().build()
           }
            return dbInstance!!
        }
    }
}