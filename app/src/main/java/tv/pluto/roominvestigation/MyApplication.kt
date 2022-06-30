package tv.pluto.roominvestigation

import android.app.Application
import android.os.Process
import android.util.Log
import androidx.work.Configuration
import androidx.work.Configuration.Provider

class MyApplication : Application(), Provider {

    override fun getWorkManagerConfiguration(): Configuration {
        Log.d("sansay", "MyApplication.getWorkManagerConfiguration() ${Process.myPid()}")
        return WorkConfigurationProvider().get()
    }
}