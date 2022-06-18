package com.example.traininglog.gorny.treningovy_zapisnik.data

import android.content.res.Resources
import com.example.traininglog.gorny.treningovy_zapisnik.R

fun runAchievementList(): List<AchievementRow> {
    return listOf(
            AchievementRow(
                id = 1,
                logType =  "Run",
                achType = "Distance",
                imageOfType = R.drawable.run,
                description = "Run total 10 km",
                current = 20.0,
                max =  10.0,
                completed = false
            ),
            AchievementRow(
                id = 2,
                logType = "Swim",
                achType = "Distance",
                imageOfType = R.drawable.swim,
                description = "Swim total 1 km",
                current = 0.0,
                max = 1000.0,
                completed = false
            ),
            AchievementRow(
                id = 3,
                logType = "Bike",
                achType = "Distance",
                imageOfType = R.drawable.bike,
                description = "Bike total 100 km",
                current = 0.0,
                max = 100.0,
                completed = false
            ),
    )

}