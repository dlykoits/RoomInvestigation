package tv.pluto.roominvestigation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import tv.pluto.roominvestigation.workmanager.TempWorker
import tv.pluto.roominvestigation.workmanager.WorkHelper

class SecondProcessBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.getBooleanExtra(EXTRA_USE_WORKER, false) == true) {
            val useRemote = intent.getBooleanExtra(EXTRA_USE_REMOTE, false)
            for (i in 1..100) {
                if(useRemote) {
                    WorkHelper.startRemoteWorker(context)
                } else {
                    WorkHelper.startWorker(context)
                }
            }
        } else {
            Thread {
                context?.let {
                    for (i in 1..100) {
                        TempWorker.dbWork(it)
                    }
                }
            }.start()
        }
    }

    companion object {

        const val EXTRA_USE_WORKER = "use_worker"
        const val EXTRA_USE_REMOTE = "use_remote"

        fun start(applicationContext: Context?, useWorker: Boolean = false, useRemote: Boolean = false) {
            applicationContext?.let {
                val i = Intent(it, SecondProcessBroadcastReceiver::class.java)
                i.putExtra(EXTRA_USE_WORKER, useWorker)
                i.putExtra(EXTRA_USE_REMOTE, useRemote)
                it.sendBroadcast(i)
            }
        }
    }
}