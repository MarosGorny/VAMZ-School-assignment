package com.example.traininglog.gorny.treningovy_zapisnik.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
   // private val initialTrainingLogList = trainingLogsList(resources)
    //private val trainingLogsLiveData = MutableLiveData(initialTrainingLogList)

    private val initialAchievementList = runAchievementList()
    private val runAchievementsLiveData = MutableLiveData(initialAchievementList)

    private val _distanceOfRunning = MutableLiveData<Double>()
    private var _distanceOfBike = MutableLiveData<Double>()
    private var _distanceOfSwim = MutableLiveData<Double>()

    fun getDistanceOfRunning(): LiveData<Double> {
        return _distanceOfRunning
    }

    fun getDistanceOfBike(): LiveData<Double> {
        return _distanceOfBike
    }

    fun addDistance(logType: String, distance:Double) {
        when(logType) {
            "Run" -> _distanceOfRunning.value = _distanceOfRunning.value?.plus(distance)
            "Bike" -> _distanceOfBike.value = _distanceOfBike.value?.plus(distance)
            "Swim" -> _distanceOfSwim.value = _distanceOfSwim.value?.plus(distance)
        }
    }

/*
    /* Adds training log to liveData and posts value. */
    fun addTrainingLog(trainingLog : TrainingLogRow) {
        val currentList = trainingLogsLiveData.value
        if(currentList == null) {
            //Posts a task to a main thread to set the given value.
            trainingLogsLiveData.postValue(listOf(trainingLog))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0,trainingLog)
            //Posts a task to a main thread to set the given value.
            trainingLogsLiveData.postValue(updatedList)
        }
    }

    /*Removes training log from liveData and post value. */
    fun removeTrainingLog(trainingLog: TrainingLogRow) {
        val currentList = trainingLogsLiveData.value
        if(currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(trainingLog)

            //Posts a task to a main thread to set the given value.
            trainingLogsLiveData.postValue(updatedList)
        }
    }

    fun updateAchievementCurrent(logType: String,distance: Double) {

    }

    /* Returns training log given an ID. */
    fun getTrainingLogId(id: Long): TrainingLogRow? {

        trainingLogsLiveData.value?.let {  trainingLogs ->
            //Returns the first element matching the given predicate, or null if element was not found.
            return trainingLogs.firstOrNull{it.id == id}
        }
        return null
    }

    fun getAchievementById(id: Int): AchievementRow? {

        runAchievementsLiveData.value?.let {  achievementLogs ->
            //Returns the first element matching the given predicate, or null if element was not found.
            return achievementLogs.firstOrNull{it.id == id}
        }
        return null
    }

    fun getTrainingLogList(): LiveData<List<TrainingLogRow>> {
        return trainingLogsLiveData
    }
 */
    fun getRunAchievementList(): LiveData <List<AchievementRow>> {
        return runAchievementsLiveData
    }




    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}

