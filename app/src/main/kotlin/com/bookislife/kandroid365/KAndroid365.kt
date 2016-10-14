package com.bookislife.kandroid365

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

/**
 * Created by SidneyXu on 2016/05/10.
 */
class KAndroid365 : Application() {

    override fun onCreate() {
        super.onCreate()

        // Log
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // LeakCanary
        LeakCanary.install(this)

        // Realm
        val config = RealmConfiguration.Builder(this)
                .schemaVersion(1)
                .migration { dynamicRealm, oldVersion, newVersion ->
                    if (oldVersion == 0L) {

                    }
                    println("oldVersion=$oldVersion and newVersion=$newVersion")
                }
                .build()
        Realm.setDefaultConfiguration(config)


    }
}