package tv.pluto.roominvestigation.workmanager

import android.content.Context
import android.os.Process
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import tv.pluto.roominvestigation.room.RoomHelper
import tv.pluto.roominvestigation.room.User
import kotlin.random.Random

class TempWorker(appContext: Context, val workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        dbWork(applicationContext)
        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    companion object {
        fun dbWork(applicationContext: Context) {
            Log.d("sansay", "Process: ${Process.myPid()}")
            Log.d("sansay", "open: ${Thread.currentThread().id}")
            // Do the work here--in this case, upload the images.
            val db = RoomHelper.createDb(applicationContext)

            val users = mutableListOf<User>()
            val random = Random(100000)
            for (i in 1..100000) {
                users.add(
                    User(
                        i,
                        "user ${random.nextInt()}",
                        "lastname $i"
                    )
                )
            }
            db.userDao().insertAll(users)
            db.close()
            Log.d("sansay", "close: ${Thread.currentThread().id}")
        }
    }
}