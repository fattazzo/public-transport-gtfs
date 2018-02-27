package com.gmail.fattazzo.gtfsdb.entities

import com.activeandroid.Model
import com.activeandroid.annotation.Column
import com.activeandroid.query.Select
import java.io.Serializable

/**
 * @author fattazzo
 *         <p/>
 *         date: 13/02/18
 */
abstract class BaseModel : Model(), Serializable {

    @Column(length = 50, notNull = true, index = true)
    lateinit var feedId: String

    companion object {

        fun count(clazz: Class<out Model>): Int = Select().from(clazz).count()

        fun <T : Model> loadAll(clazz: Class<T>): List<T> {
            return Select().from(clazz).execute<T>()
        }
    }
}