package kr.co.example.codingtest_hand.database

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kr.co.example.codingtest_hand.database.dao.RankingDAO
import kr.co.example.codingtest_hand.database.entity.Ranking
import kr.co.example.codingtest_hand.utils.ioThread

import javax.inject.Singleton


@Database(entities = [Ranking::class], version = 1, exportSchema = false)
//@TypeConverters(Converts::class)
@Singleton
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null
        private const val DB_NAME = "CT_Hand.db"

        fun init(context: Context) {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDatabase::class.java, DB_NAME
                    ).addMigrations(MIGRATION_1_2).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            ioThread {
                                getInstance().rankingDAO().insertRankingList(dummyData)
                            }

                        }
                    }).build()
                }
            }

            //dummy
            ioThread {
                if (getInstance().rankingDAO().dummySelect().isEmpty()) {
                    getInstance().rankingDAO()
                        .insertRankingList(listOf(Ranking("A",  10, 10, 0, 0, 30)))
                }
            }

        }


        fun getInstance() = instance!!

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }


        val dummyData = listOf(
            Ranking("B",  10, 8, 1, 1, 25),
            Ranking("C",  10, 7, 2, 1, 23),
            Ranking("D",  10, 7, 1, 2, 22),
            Ranking("E",  10, 6, 3, 1, 21),
            Ranking("F",  10, 5, 5, 0, 20),
            Ranking("G",  10, 3, 3, 4, 12)
        )
    }

    abstract fun rankingDAO(): RankingDAO


}