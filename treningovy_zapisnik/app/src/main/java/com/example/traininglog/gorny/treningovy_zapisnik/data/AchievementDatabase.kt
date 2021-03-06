package com.example.traininglog.gorny.treningovy_zapisnik.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Databaza pre achievementy
 */
@Database(entities = [AchievementRow::class], version = 6, exportSchema = false)
abstract class AchievementDatabase: RoomDatabase() {

    abstract fun achievementDao(): AchievementDao

    companion object {
        @Volatile
        private var INSTANCE: AchievementDatabase? = null

        fun getDatabase(context: Context): AchievementDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AchievementDatabase::class.java,
                    "achievement_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/achievements.db")
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}