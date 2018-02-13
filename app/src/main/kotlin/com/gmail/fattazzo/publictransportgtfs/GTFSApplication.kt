package com.gmail.fattazzo.publictransportgtfs

import android.app.Application
import android.content.Context
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import com.activeandroid.serializer.BigDecimalSerializer
import com.gmail.fattazzo.publictransportgtfs.db.gtfs.*
import org.androidannotations.annotations.EApplication


/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@EApplication
open class GTFSApplication : Application() {

    private var DB_NAME = "gtfsdb"

    override fun onCreate() {
        super.onCreate()

        ActiveAndroid.initialize(buildActiveAndroidConfiguration(this))
    }

    private fun buildActiveAndroidConfiguration(context: Context): Configuration {
        return Configuration.Builder(context)
                .setDatabaseName(DB_NAME)
                // entities
                .addModelClasses(Agency::class.java,
                        Calendar::class.java,
                        CalendarDate::class.java,
                        FeedInfo::class.java,
                        Route::class.java,
                        Shape::class.java,
                        Stop::class.java,
                        StopTime::class.java,
                        Transfer::class.java,
                        Trip::class.java
                )
                // serializer
                .addTypeSerializer(BigDecimalSerializer::class.java)
                .create()
    }
}