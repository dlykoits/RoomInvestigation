package tv.pluto.roominvestigation.workmanager

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.multiprocess.RemoteWorkManager

class WorkHelper {

    companion object {
        private val tempWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<TempWorker>()
                .build()

        fun startWorker(context: Context?) {
            context?.let {
                WorkManager
                    .getInstance(it)
                    .enqueue(tempWorkRequest)
            }
        }

        fun startRemoteWorker(context: Context?) {
            context?.let {
                //RemoteWorkManager
                RemoteWorkManager
                    .getInstance(it)
                    .enqueue(tempWorkRequest)
            }
        }
    }
}