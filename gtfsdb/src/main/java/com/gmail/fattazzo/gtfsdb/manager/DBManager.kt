package com.gmail.fattazzo.gtfsdb.manager

import android.content.Context
import com.activeandroid.ActiveAndroid
import com.activeandroid.Configuration
import com.activeandroid.serializer.BigDecimalSerializer
import com.gmail.fattazzo.gtfsdb.entities.*
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext
import java.lang.Exception

/**
 * @author fattazzo
 *         <p/>
 *         date: 19/02/18
 */
@EBean(scope = EBean.Scope.Singleton)
open class DBManager {

    @RootContext
    lateinit var context: Context

    fun init() {
        try {
            ActiveAndroid.dispose()
        } catch (e: Exception) {

        }
        ActiveAndroid.initialize(buildActiveAndroidConfiguration(DB_NAME))
    }

    fun recreate() {
        delete()
        init()
    }

    private fun delete() {
        try {
            ActiveAndroid.dispose()
        } catch (e: Exception) {

        }
        context.deleteDatabase(DB_NAME)
    }

    private fun buildActiveAndroidConfiguration(dbName: String): Configuration {
        return Configuration.Builder(context)
                .setCacheSize(1)
                .setDatabaseName(dbName)
                // entities
                .addModelClasses(Agency::class.java,
                        Calendar::class.java,
                        CalendarDate::class.java,
                        FareAttribute::class.java,
                        FareRule::class.java,
                        FeedInfo::class.java,
                        Route::class.java,
                        Shape::class.java,
                        Stop::class.java,
                        StopTime::class.java,
                        Transfer::class.java,
                        Trip::class.java,
                        Operator::class.java)
                // serializer
                .addTypeSerializer(BigDecimalSerializer::class.java)
                .create()
    }

    companion object {

        private const val DB_NAME = "gtfsdb"
    }
}