package com.example.recyclerviewdemov2

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper(context, name, factory, version) {

    //This only run once after app installation
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_MOVIE_TABLE = ("CREATE TABLE " +
                TABLE_MOVIE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_RATING + " REAL, " +
                COLUMN_POSTER + " TEXT" + ")"
                )

        db.execSQL(CREATE_MOVIE_TABLE)
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(1, 'The Shawshank Redemption', 9.2, 'movie01')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(2, 'The God Father', 9.2, 'movie02')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(3, 'The God Father: Part II', 9.0, 'movie03')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(4, 'The Dark Night', 9.0, 'movie04')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(5, '12 Angry Men', 8.9, 'movie05')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(6, 'Schindlers The List', 8.9, 'movie06')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(7, 'The Lord of The Ring: The Return of the King', 8.9, 'movie07')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(8, 'Pulp Fiction', 8.9, 'movie08')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(9, 'The Good, the Bad and the Ugly', 8.8, 'movie09')")
        db.execSQL("INSERT INTO ${TABLE_MOVIE} VALUES(10, 'Fight Club', 8.0, 'movie10')")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${TABLE_MOVIE}")
        onCreate(db)
    }

    fun getAll(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOVIE order by $COLUMN_RATING", null)
    }

    fun getNinePoints(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOVIE WHERE $COLUMN_RATING>=9.0", null)
    }

    fun getEightToNine(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOVIE WHERE $COLUMN_RATING>=8.0 AND $COLUMN_RATING<9.0", null)
    }

    fun getFromTo(from: Double, to: Double): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOVIE WHERE $COLUMN_RATING>=$from AND $COLUMN_RATING<$to", null)
    }


    fun search(keyword: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_MOVIE WHERE $COLUMN_TITLE LIKE '$keyword'", null)

    }

//    fun getHotItems() {}
//
//    fun getBrands() {}
//
//    fun search(keyword: String) {}
//
//    fun getSpecialPriceItem() {}

    companion object {
        private val DATABASE_NAME = "movies.db"
        val TABLE_MOVIE = "movies"
        val COLUMN_ID = "_id"
        val COLUMN_TITLE = "title"
        val COLUMN_RATING = "rating"
        val COLUMN_POSTER = "poster_url"
    }


}