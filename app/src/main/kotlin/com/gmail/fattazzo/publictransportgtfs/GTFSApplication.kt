package com.gmail.fattazzo.publictransportgtfs

import android.app.Application
import com.gmail.fattazzo.gtfsdb.manager.DBManager
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EApplication


/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
@EApplication
open class GTFSApplication : Application() {

    @Bean
    lateinit var dbManager: DBManager

    override fun onCreate() {
        super.onCreate()

        dbManager.init()
    }
}